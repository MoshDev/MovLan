package space.ersan.movlan.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.postDelayed
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.view_main.view.*
import space.ersan.movlan.R
import space.ersan.movlan.common.view.EndlessRecyclerViewScrollListener
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.home.list.MoviesListAdapter
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class HomeView(context: Context, thumbnailLoader: ImageLoader.Thumbnail) : FrameLayout(
    context) {

  private val adapter: MoviesListAdapter = MoviesListAdapter(thumbnailLoader)
  private val layoutManger = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

  init {
    View.inflate(context, R.layout.view_main, this)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = layoutManger
  }

  fun setMovies(result: PagedList<Movie>) {
    adapter.submitList(result)
  }

  fun observeMovieListClicks(clb: (Movie) -> Unit) {
  }

  fun observeSwipeToRefresh(clb: () -> Unit) {
    swipeToRefresh.setOnRefreshListener(clb)
  }

  fun setRefreshIndicator(refresh: Boolean) {
    swipeToRefresh.isRefreshing = refresh
  }

}
