package space.ersan.movlan.data.source.local

import androidx.paging.PagedList
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe

class MoviesDbBoundaryCallback(private val cor: AppCoroutineDispatchers,
                               private val remoteDataSource: MoviesRemoteDataSource,
                               private val localDataSource: MoviesLocalDataSource)
  : PagedList.BoundaryCallback<Movie>() {

  override fun onZeroItemsLoaded() {
    val page = 1
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(page)
      when (result) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.deleteAll()
          localDataSource.insertAll(page, result.value.results ?: emptyList())
        }
      }
    }
  }

  override fun onItemAtEndLoaded(itemAtEnd: Movie) {
    val nextPage = itemAtEnd.page.inc()
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(nextPage)
      when (result) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.insertAll(nextPage,
              result.value.results ?: emptyList())
        }
      }
    }
  }

}