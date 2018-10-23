package space.ersan.movlan.data.source.remote.search

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe

class SearchDataSource(private val cor: AppCoroutineDispatchers,
                       private val remoteDataSource: MoviesRemoteDataSource,
                       private val query: String)
  : PageKeyedDataSource<SearchQuery, Movie>() {

  override fun loadInitial(params: LoadInitialParams<SearchQuery>, callback: LoadInitialCallback<SearchQuery, Movie>) {
    val page = 1
    launch(cor.NETWORK) {
      val result = remoteDataSource.search(query, page)
      when (result) {
        is Maybe.Some -> callback.onResult(result.value.results!!.sortedByDescending(::sortByPopularity),
            null,
            SearchQuery(query, page.inc()))
        is Maybe.Error -> result.error.printStackTrace()
      }
    }
  }

  override fun loadAfter(params: LoadParams<SearchQuery>, callback: LoadCallback<SearchQuery, Movie>) {
    val page = params.key.page
    val query = params.key.query
    launch(cor.NETWORK) {
      val result = remoteDataSource.search(query, page)
      when (result) {
        is Maybe.Some -> callback.onResult(result.value.results!!.sortedByDescending(::sortByPopularity),
            SearchQuery(query, page.inc()))
        is Maybe.Error -> result.error.printStackTrace()
      }
    }
  }

  private fun sortByPopularity(it: Movie): Double = it.popularity ?: -1.0

  override fun loadBefore(params: LoadParams<SearchQuery>, callback: LoadCallback<SearchQuery, Movie>) {
  }
}

data class SearchQuery(val query: String, val page: Int = 1)