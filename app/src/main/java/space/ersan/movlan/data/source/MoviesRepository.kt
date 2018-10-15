package space.ersan.movlan.data.source

//import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallback
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe


class MoviesRepository(private val cor: AppCoroutineDispatchers,
                       private val localDataSource: MoviesLocalDataSource,
                       private val remoteDataSource: MoviesRemoteDataSource,
                       private val moviesDbBoundaryCallback: MoviesDbBoundaryCallback) {

  fun getPopularMovies(): LiveData<PagedList<Movie>> {
    return localDataSource.getPopularMovies(1)
        .toLiveData(pageSize = 20, initialLoadKey = 1, boundaryCallback = moviesDbBoundaryCallback)
  }

  fun getGenres(clb: (Maybe<GenreList>) -> Unit) {
    launch(cor.NETWORK) {
      val result = remoteDataSource.getGenres()
      withContext(cor.UI) {
        clb(result)
      }
    }
  }

  fun getMovieDetails(movieId: Int, callback: (Movie) -> Unit) {
  }

  fun invalidate() {
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(1)
      when (result) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.deleteAll()
          localDataSource.insertAll(1, result.value.results!!)
        }
      }
    }
  }
}