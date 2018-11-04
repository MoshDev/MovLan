package space.ersan.movlan.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.local.LocalDataSource
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallbackFactory
import space.ersan.movlan.data.source.remote.RemoteDataSource
import space.ersan.movlan.data.source.remote.search.SearchDataSourceFactory
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.Maybe
import space.ersan.movlan.utils.NetworkStatus

class DefaultMoviesRepository(private val cor: AppCoroutineDispatchers,
                              private val localDataSource: LocalDataSource,
                              private val remoteDataSource: RemoteDataSource,
                              private val moviesDbBoundaryCallbackFactory: MoviesDbBoundaryCallbackFactory,
                              private val networkStatus: LiveNetworkStatus) : MoviesRepository {

  override fun searchMovies(query: String, page: Int, sorting: (Movie) -> Double, callback: (Maybe<MovieList>) -> Unit) {
    GlobalScope.launch(cor.NETWORK) {
      val result = remoteDataSource.search(query, page)
      callback(result)
    }
  }

  override fun loadPopularMovies(page: Int) {
    GlobalScope.launch(cor.NETWORK) {
      if (page == 1) {
        val genres = remoteDataSource.getGenres()
        when (genres) {
          is Maybe.Some -> withContext(cor.IO) { localDataSource.insertAllGenres(genres.value.genres!!) }
          is Maybe.Error -> {
            networkStatus.postValue(NetworkStatus.Error { loadPopularMovies(page) })
            return@launch
          }
        }
      }
      val movies = remoteDataSource.getPopularMovies(page)
      when (movies) {
        is Maybe.Some -> withContext(cor.IO) {
          if (page == 1) localDataSource.deleteAllMovies()
          localDataSource.insertAll(page, movies.value.results ?: emptyList())
          networkStatus.postValue(NetworkStatus.Loaded)
        }
        is Maybe.Error -> {
          networkStatus.postValue(NetworkStatus.Error { loadPopularMovies(page) })
        }
      }
    }
  }

  override fun getPopularMoviesPaginated(): LiveData<PagedList<Movie>> {
    return localDataSource.getMovies()
        .toLiveData(pageSize = 20,
            initialLoadKey = 1,
            boundaryCallback = moviesDbBoundaryCallbackFactory.createCallback(this))
  }

  override fun getMovieDetails(movieId: Int): LiveData<Movie> {
    val movieData = MutableLiveData<Movie>()
    GlobalScope.launch(cor.IO) {
      val localMovie = localDataSource.getMovie(movieId)
      if (localMovie != null) {
        movieData.postValue(localMovie)
      }
      withContext(cor.NETWORK) {
        val remoteMovie = remoteDataSource.getMovieDetails(movieId)
        if (remoteMovie is Maybe.Some) {
          movieData.postValue(remoteMovie.value)
        }
      }
    }
    return movieData
  }

  override fun invalidate() {
    networkStatus.postValue(NetworkStatus.Loading)
    GlobalScope.launch(cor.NETWORK) {
      val genres = remoteDataSource.getGenres()
      when (genres) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.deleteAllGenres()
          localDataSource.insertAllGenres(genres.value.genres!!)
        }
        is Maybe.Error -> {
          networkStatus.postValue(NetworkStatus.Error(::invalidate))
          return@launch
        }
      }
      val movies = remoteDataSource.getPopularMovies(1)
      when (movies) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.insertAll(1, movies.value.results!!)
          localDataSource.deleteAllMoviesExcept(1)
          networkStatus.postValue(NetworkStatus.Loaded)
        }
        is Maybe.Error -> networkStatus.postValue(NetworkStatus.Error())
      }
    }
  }

  override fun searchMovies(query: String): LiveData<PagedList<Movie>> {
    val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .build()

    return LivePagedListBuilder(/*TODO to be injected somehow*/SearchDataSourceFactory(this, query),
        pagedListConfig)
        .build()
  }
}