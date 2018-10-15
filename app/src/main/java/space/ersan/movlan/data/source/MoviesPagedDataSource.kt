package space.ersan.movlan.data.source

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe

class MoviesPagedDataSource(private val cor: AppCoroutineDispatchers,
                            private val remoteDataSource: MoviesRemoteDataSource)
  : PageKeyedDataSource<Int, Movie>() {

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
    println("Mosh loadInitial 1")
    val page = 1
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(page)
      println("Mosh loadInitial ${result}")
      when (result) {
        is Maybe.Some -> {
          withContext(cor.UI) {
            callback.onResult(result.value.results!!,
                null,
                page + 1)
          }
        }
        is Maybe.Error -> result.error.printStackTrace()
      }
    }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    val page = params.key
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(page)
      when (result) {
        is Maybe.Some -> withContext(cor.UI) {
          callback.onResult(result.value.results!!, page + 1)
        }
      }
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
  }
}