package space.ersan.movlan.ui.feed.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.doOnLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import space.ersan.movlan.R
import space.ersan.movlan.common.BaseView
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.ui.feed.FeedMovies
import space.ersan.movlan.ui.feed.view.content.FeedListingView
import space.ersan.movlan.ui.feed.view.content.FeedNowPlayingView
import space.ersan.movlan.utils.NetworkStatus

class FeedView(context: Context) : BaseView, NativeView {

  private val view: View = LayoutInflater.from(context).inflate(R.layout.view_feed, null)
  private val feedNowPlayingView = view.findViewById<FeedNowPlayingView>(R.id.feedNowPlayingView)
  private val feedListingView = view.findViewById<FeedListingView>(R.id.feedListingView)


  init {

    BottomSheetBehavior.from(feedListingView).run {
      isHideable = true
      setBottomSheetCallback(FeedBottomSheetCallback {
        feedNowPlayingView.onSlide(this, it)
      })
      feedNowPlayingView.doOnLayout {
        peekHeight = view.height - feedNowPlayingView.height
      }
    }
  }

  fun setFeedMovies(feedMovies: FeedMovies) {
    feedNowPlayingView.setNowPlayingMovies(feedMovies.nowPlaying.movies!!)
    feedListingView.setMovies(feedMovies.popular, feedMovies.upcoming)

  }

  override fun getView() = view

  fun setNetworkStatus(networkStatus: NetworkStatus) {
    if (networkStatus is NetworkStatus.Error) {
      Snackbar.make(getView(), "Failed To Load", Snackbar.LENGTH_INDEFINITE).setAction("Retry") {
        networkStatus.retry?.invoke()
      }.show()
    }
  }
}

private class FeedBottomSheetCallback(private val clb: (slideOffset: Float) -> Unit) :
  BottomSheetBehavior.BottomSheetCallback() {
  override fun onSlide(bottomSheet: View, slideOffset: Float) {
    clb(slideOffset)
  }

  override fun onStateChanged(bottomSheet: View, state: Int) {
  }
}