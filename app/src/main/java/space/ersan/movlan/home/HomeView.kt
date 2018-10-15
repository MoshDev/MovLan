package space.ersan.movlan.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import space.ersan.movlan.R
import space.ersan.movlan.common.mvp.BaseView
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.home.list.MoviesListAdapter
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.utils.NetworkStatus

@SuppressLint("ViewConstructor")
class HomeView(context: Context, posterLoader: ImageLoader.Poster)
  : BaseView(context) {

  private val adapter: MoviesListAdapter = MoviesListAdapter(posterLoader)
  private val layoutManger = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
  private val recyclerView: RecyclerView
  private val swipeToRefresh: SwipeRefreshLayout
  private val progressBar: ContentLoadingProgressBar
  private var shownSnackbar: Snackbar? = null

  init {
    View.inflate(context, R.layout.view_main, this)
    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = layoutManger

    swipeToRefresh = findViewById(R.id.swipeToRefresh)
    progressBar = findViewById(R.id.progressBar)
  }

  fun setMovies(result: PagedList<Movie>) {
    adapter.submitList(result)
  }

  fun observeMovieListClicks(clb: (Movie) -> Unit) {
    adapter.clicksCallback = clb
  }

  fun observeSwipeToRefresh(clb: () -> Unit) {
    swipeToRefresh.setOnRefreshListener {
      clb()
      swipeToRefresh.isRefreshing = false
    }
  }

  fun setNetworkStatus(networkStatus: NetworkStatus) {
    when (networkStatus) {
      is NetworkStatus.Loading -> showLoadingBar(true)
      is NetworkStatus.Loaded -> showLoadingBar(false)
      is NetworkStatus.Error -> showErrorSnackBar(networkStatus)
    }
  }

  private fun showErrorSnackBar(networkStatus: NetworkStatus.Error) {
    showLoadingBar(false)
    val snackbar = Snackbar.make(this,
        R.string.network_error_home_movies_list,
        Snackbar.LENGTH_INDEFINITE)
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

}
