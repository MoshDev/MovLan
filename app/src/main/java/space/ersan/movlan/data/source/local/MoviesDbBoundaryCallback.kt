package space.ersan.movlan.data.source.local

import androidx.paging.PagedList
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe
import java.util.concurrent.atomic.AtomicBoolean

class MoviesDbBoundaryCallback(private val cor: AppCoroutineDispatchers,
                               private val remoteDataSource: MoviesRemoteDataSource,
                               private val localDataSource: MoviesLocalDataSource)
  : PagedList.BoundaryCallback<Movie>() {

  override fun onZeroItemsLoaded() {
    println("Mosh onZeroItemsLoaded")
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(1)
      when (result) {
        is Maybe.Some -> {
          withContext(cor.IO) {
            localDataSource.deleteAll()
            localDataSource.insertAll(1, result.value.results ?: emptyList())
          }
        }
      }
    }
  }

  private val isLoading = AtomicBoolean(false)

  override fun onItemAtEndLoaded(itemAtEnd: Movie) {
    if (isLoading.get()) {
      println("Mosh isLoading ${isLoading.get()} for ${itemAtEnd.page}")
      return
    }
    isLoading.set(true)
    println("Mosh onItemAtEndLoaded page= ${itemAtEnd.page}")
    launch(cor.NETWORK) {
      val nextPage = itemAtEnd.page.inc()
      val result = remoteDataSource.getPopularMovies(nextPage)
      println("Mosh: loading page $nextPage")
      when (result) {
        is Maybe.Some -> {
          withContext(cor.IO) {
            localDataSource.insertAll(nextPage, result.value.results ?: emptyList())
            isLoading.set(false)
          }
        }
      }
    }
  }

}