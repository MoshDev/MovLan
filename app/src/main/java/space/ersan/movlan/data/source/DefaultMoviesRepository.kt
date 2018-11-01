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
import space.ersan.movlan.data.source.local.LocalDataSource
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallback
import space.ersan.movlan.data.source.remote.RemoteDataSource
import space.ersan.movlan.data.source.remote.search.SearchDataSourceFactory
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.Maybe
import space.ersan.movlan.utils.NetworkStatus

class DefaultMoviesRepository(private val cor: AppCoroutineDispatchers,
                              private val localDataSource: LocalDataSource,
                              private val remoteDataSource: RemoteDataSource,
                              private val moviesDbBoundaryCallback: MoviesDbBoundaryCallback,
                              private val networkStatus: LiveNetworkStatus) : MoviesRepository {

  override fun getPopularMovies(): LiveData<PagedList<Movie>> {
    return localDataSource.getMovies()
        .toLiveData(pageSize = 20, initialLoadKey = 1, boundaryCallback = moviesDbBoundaryCallback)
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

    return LivePagedListBuilder(SearchDataSourceFactory(cor, remoteDataSource, query),
        pagedListConfig)
        .build()
  }
}