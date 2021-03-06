package space.ersan.movlan.ui.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.ContentLoadingProgressBar
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import space.ersan.movlan.R
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.ui.movie.list.MoviesListAdapter
import space.ersan.movlan.utils.NetworkStatus
import space.ersan.themoviedbapi.model.movie.Movie

@SuppressLint("ViewConstructor")
class DefaultMoviesListingView(context: Context, posterLoader: ImageLoader.Poster) :
  FrameLayout(context), MoviesListingView, NativeView {

  private val adapter: MoviesListAdapter = MoviesListAdapter(posterLoader)
  private val layoutManger = StaggeredGridLayoutManager(
    context.resources.getInteger(R.integer.listing_movies_span),
    StaggeredGridLayoutManager.VERTICAL
  )
  private val recyclerView: RecyclerView
  private val swipeToRefresh: SwipeRefreshLayout
  private val progressBar: ContentLoadingProgressBar
  private var shownSnackbar: Snackbar? = null

  init {
    View.inflate(context, R.layout.view_movie_listing, this)
    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = layoutManger

    swipeToRefresh = findViewById(R.id.swipeToRefresh)
    swipeToRefresh.isEnabled = false
    progressBar = findViewById(R.id.progressBar)
    progressBar.hide()
  }

  override fun setMovies(result: PagedList<Movie>) {
    adapter.submitList(result)
  }

  override fun observeMovieListClicks(clb: (Context, Movie) -> Unit) {
    adapter.clicksCallback = {
      clb(context, it)
    }
  }

  override fun observeSwipeToRefresh(clb: () -> Unit) {
    swipeToRefresh.isEnabled = true
    swipeToRefresh.setOnRefreshListener {
      clb()
      swipeToRefresh.isRefreshing = false
    }
  }

  override fun setNetworkStatus(networkStatus: NetworkStatus) {
    when (networkStatus) {
      is NetworkStatus.Loading -> showLoadingBar(true)
      is NetworkStatus.Loaded -> showLoadingBar(false)
      is NetworkStatus.Error -> showErrorSnackBar(networkStatus)
    }
  }

  private fun showErrorSnackBar(networkStatus: NetworkStatus.Error) {
    showLoadingBar(false)
    val snackbar = Snackbar.make(
      this,
      R.string.network_error_home_movies_list,
      Snackbar.LENGTH_INDEFINITE
    )
      .setAction(R.string.retry) {
        networkStatus.retry?.invoke()
      }
    snackbar.show()
    shownSnackbar = snackbar
  }

  private fun hidePreviousSnackbar() {
    if (shownSnackbar != null && shownSnackbar?.isShown!!) {
      shownSnackbar?.dismiss()
      shownSnackbar = null
    }
  }

  private fun showLoadingBar(visible: Boolean) {
    hidePreviousSnackbar()
    if (visible) {
      progressBar.show()
    } else {
      progressBar.hide()
    }
  }

  override fun getView() = this
}
