package space.ersan.movlan.data.source.remote.search

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.Maybe

class MoviesSearchDataSource(private val repository: MoviesRepository,
                             private val query: String)
  : PageKeyedDataSource<SearchQuery, Movie>() {

  override fun loadInitial(params: LoadInitialParams<SearchQuery>, callback: LoadInitialCallback<SearchQuery, Movie>) {
    GlobalScope.launch {
      val page = 1
      repository.searchMovies(query, page, ::sortByPopularity) {
        when (it) {
          is Maybe.Some -> callback.onResult(it.value.results!!,
              null,
              SearchQuery(query, page.inc()))
          is Maybe.Error -> it.error.printStackTrace()
        }
      }
    }
  }

  override fun loadAfter(params: LoadParams<SearchQuery>, callback: LoadCallback<SearchQuery, Movie>) {
    GlobalScope.launch {
      val page = params.key.page
      val query = params.key.query
      repository.searchMovies(query, page, ::sortByPopularity) { result ->
        when (result) {
          is Maybe.Some -> callback.onResult(result.value.results!!,
              SearchQuery(query, page.inc()))
          is Maybe.Error -> result.error.printStackTrace()
        }
      }
    }
  }

  private fun sortByPopularity(it: Movie): Double = it.popularity ?: -1.0

  override fun loadBefore(params: LoadParams<SearchQuery>, callback: LoadCallback<SearchQuery, Movie>) {
  }
}

data class SearchQuery(val query: String, val page: Int = 1)