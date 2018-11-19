package space.ersan.movlan.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.local.LocalDataSource
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallbackFactory
import space.ersan.movlan.data.source.remote.RemoteDataSource
import space.ersan.movlan.data.source.remote.search.SearchDataSourceFactory
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.Maybe
import space.ersan.movlan.utils.NetworkStatus

class DefaultMoviesRepository(
  private val localDataSource: LocalDataSource,
  private val remoteDataSource: RemoteDataSource,
  private val moviesDbBoundaryCallbackFactory: MoviesDbBoundaryCallbackFactory
) : MoviesRepository {

  override suspend fun searchMovies(
    query: String,
    page: Int,
    sorting: (Movie) -> Double,
    callback: (Maybe<MovieList>) -> Unit
  ) {
    callback(remoteDataSource.search(query, page))
  }

  override suspend fun loadPopularMovies(page: Int, networkStatus: LiveNetworkStatus) {
    if (page == 1) {
      val genres = remoteDataSource.getGenres()
      when (genres) {
        is Maybe.Some -> localDataSource.insertAllGenres(genres.value.genres!!)
        is Maybe.Error -> {
          networkStatus.postValue(NetworkStatus.Error {
            suspend {
              loadPopularMovies(
                page,
                networkStatus
              )
            }
          })
          return
        }
      }
    }

    val movies = remoteDataSource.getPopularMovies(page)
    when (movies) {
      is Maybe.Some -> {
        if (page == 1) localDataSource.deleteAllMovies()
        localDataSource.insertAll(page, movies.value.results ?: emptyList())
        networkStatus.postValue(NetworkStatus.Loaded)
      }
      is Maybe.Error -> {
        networkStatus.postValue(NetworkStatus.Error {
          suspend {
            loadPopularMovies(
              page,
              networkStatus
            )
          }
        })
      }
    }
  }

  override suspend fun getPopularMoviesPaginated(networkStatus: LiveNetworkStatus): LiveData<PagedList<Movie>> {
    return localDataSource.getMovies()
      .toLiveData(
        pageSize = 20,
        initialLoadKey = 1,
        boundaryCallback = moviesDbBoundaryCallbackFactory.createCallback(
          this,
          networkStatus
        )
      )
  }

  override suspend fun getMovieDetails(movieId: Int, clb: (Movie) -> Unit) {
    val localMovie = localDataSource.getMovie(movieId)
    if (localMovie != null) {
      clb(localMovie)
    }
    val remoteMovie = remoteDataSource.getMovieDetails(movieId)
    if (remoteMovie is Maybe.Some) {
      clb(remoteMovie.value)
    }
  }

  override suspend fun invalidate(networkStatus: LiveNetworkStatus) {
    networkStatus.postValue(NetworkStatus.Loading)
    val genres = remoteDataSource.getGenres()
    when (genres) {
      is Maybe.Some -> {
        localDataSource.deleteAllGenres()
        localDataSource.insertAllGenres(genres.value.genres!!)
      }
      is Maybe.Error -> {
        networkStatus.postValue(NetworkStatus.Error { suspend { invalidate(networkStatus) } })
        return
      }
    }
    val movies = remoteDataSource.getPopularMovies(1)
    when (movies) {
      is Maybe.Some -> {
        localDataSource.insertAll(1, movies.value.results!!)
        localDataSource.deleteAllMoviesExcept(1)
        networkStatus.postValue(NetworkStatus.Loaded)
      }
      is Maybe.Error -> networkStatus.postValue(NetworkStatus.Error())
    }
  }

  override suspend fun searchMovies(query: String): LiveData<PagedList<Movie>> {
    val pagedListConfig = PagedList.Config.Builder()
      .setEnablePlaceholders(false)
      .setInitialLoadSizeHint(20)
      .setPageSize(20)
      .build()

    return LivePagedListBuilder(/*TODO to be injected somehow*/SearchDataSourceFactory(
      this,
      query
    ),
      pagedListConfig
    )
      .build()
  }
}