package space.ersan.movlan.data.source

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe

class MoviesPagedDataSource(private val cor: AppCoroutineDispatchers,
                            private val localDataSource: MoviesLocalDataSource,
                            private val remoteDataSource: MoviesRemoteDataSource)
  : PageKeyedDataSource<MoviesPagedDataSource.PageInfo, Movie>() {

  override fun loadInitial(params: LoadInitialParams<PageInfo>, callback: LoadInitialCallback<PageInfo, Movie>) {

//    val page = PageInfo(1, "LOCAL", 2)
//
//    launch(cor.IO) {
//      val page1 = withContext(cor.IO) { localDataSource.getPopularMovies(1) }
//      callback.onResult(page1, null, null)
//      withContext(cor.NETWORK) {
//        val items = (remoteDataSource.getPopularMovies(1) as Maybe.Some).value.results!!
//        withContext(cor.IO) {
//          localDataSource.insertAll(1, items)
//          callback.onResult(items, null, page)
//        }
//      }
//    }
  }

  override fun loadAfter(params: LoadParams<PageInfo>, callback: LoadCallback<PageInfo, Movie>) {
  }

  override fun loadBefore(params: LoadParams<PageInfo>, callback: LoadCallback<PageInfo, Movie>) {
  }

//  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
//    val page = 1
//    launch(cor.NETWORK) {
//      val result = remoteDataSource.getPopularMovies(page)
//      when (result) {
//        is Maybe.Some -> {
//          callback.onResult(result.value.results!!,
//              null,
//              page + 1)
//        }
//        is Maybe.Error -> result.error.printStackTrace()
//      }
//    }
//  }
//
//  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
//    val page = params.key
//    launch(cor.NETWORK) {
//      val result = remoteDataSource.getPopularMovies(page)
//      when (result) {
//        is Maybe.Some -> {
//          callback.onResult(result.value.results!!, page + 1)
//        }
//      }
//    }
//  }
//
//  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
//  }

  data class PageInfo(val page: Int, val source: String, val nextPage: Int)


}

