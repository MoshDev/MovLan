package space.ersan.movlan.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import space.ersan.movlan.R
import space.ersan.movlan.common.BaseView
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.movie.list.MoviesListAdapter
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class MovieSearchView(context: Context, posterLoader: ImageLoader.Poster) : BaseView(
    context) {

  private val adapter: MoviesListAdapter = MoviesListAdapter(posterLoader)
  private val layoutManger = StaggeredGridLayoutManager(context.resources.getInteger(R.integer.listing_movies_span),
      StaggeredGridLayoutManager.VERTICAL)
  private val recyclerView: RecyclerView
  private val progressBar: ContentLoadingProgressBar
  private val searchView: SearchView
  private val emptyMessageView: View

  init {
    View.inflate(context, R.layout.view_movie_search, this)
    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = layoutManger

    progressBar = findViewById(R.id.progressBar)
    progressBar.hide()

    searchView = findViewById(R.id.searchView)
    emptyMessageView = findViewById(R.id.emptyMessageView)

    adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

      override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        showEmptyMessage(true)
      }

      override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        showEmptyMessage(itemCount == 0)
      }
    })
  }

  fun setSearchQueryText(query: String?) {
    searchView.setQuery(query, false)
  }

  fun setMovies(result: PagedList<Movie>) {
    adapter.submitList(result)
  }

  private fun showEmptyMessage(visible: Boolean) {
    emptyMessageView.isVisible = visible
  }

  fun observeMovieListClicks(clb: (Context, Movie) -> Unit) {
    adapter.clicksCallback = {
      clb(context, it)
    }
  }

  fun observeSearchQuery(clb: (String) -> Unit) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

      override fun onQueryTextChange(newText: String?) = false
      override fun onQueryTextSubmit(query: String?) = with(query) {
        if (!isNullOrBlank()) {
          clb(query.toString())
          searchView.clearFocus()
          return@with true
        }
        return@with false
      }
    })
  }

  override fun onDetachedFromWindow() {
    searchView.clearFocus()
    super.onDetachedFromWindow()
  }

}