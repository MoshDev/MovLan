package space.ersan.movlan.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallback
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.Maybe
import space.ersan.movlan.utils.NetworkStatus

class MoviesRepository(private val cor: AppCoroutineDispatchers,
                       private val localDataSource: MoviesLocalDataSource,
                       private val remoteDataSource: MoviesRemoteDataSource,
                       private val moviesDbBoundaryCallback: MoviesDbBoundaryCallback,
                       private val networkStatus: LiveNetworkStatus) {

  fun getPopularMovies(): LiveData<PagedList<Movie>> {
    return localDataSource.getMovies()
        .toLiveData(pageSize = 20, initialLoadKey = 1, boundaryCallback = moviesDbBoundaryCallback)
  }

  fun getMovieDetails(movieId: Int, callback: (Movie) -> Unit) {
    TODO()
  }

  fun invalidate() {
    networkStatus.postValue(NetworkStatus.Loading)
    launch(cor.NETWORK) {
      val genres = remoteDataSource.getGenres()
      when (genres) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.deleteAllGenres()
          localDataSource.insertAllGenres(genres.value.genres!!)
        }
        is Maybe.Error -> {
          networkStatus.postValue(NetworkStatus.Error(genres.error.message, ::invalidate))
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
        is Maybe.Error -> networkStatus.postValue(NetworkStatus.Error(movies.error.message))
      }

    }
  }
}