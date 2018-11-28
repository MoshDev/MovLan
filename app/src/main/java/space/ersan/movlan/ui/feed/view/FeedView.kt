package space.ersan.movlan.ui.feed.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import space.ersan.movlan.R
import space.ersan.movlan.common.BaseView
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.ui.feed.FeedMovies
import space.ersan.movlan.utils.NetworkStatus

class FeedView(context: Context) : BaseView, NativeView {

  private val view: View = LayoutInflater.from(context).inflate(R.layout.view_feed, null)
  private val feedNowPlayingView = view.findViewById<FeedNowPlayingView>(R.id.feedNowPlayingView)

  init {

    val recyclerView = view.findViewById<RecyclerView>(R.id.bottomSheet)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = SampleAdapter()

    BottomSheetBehavior.from(recyclerView).run {
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

private class SampleAdapter : RecyclerView.Adapter<SampleVH>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleVH {
    val view = LayoutInflater.from(parent.context)
      .inflate(android.R.layout.simple_list_item_1, parent, false)
    return SampleVH(view)
  }

  override fun getItemCount(): Int = 1000

  override fun onBindViewHolder(holder: SampleVH, position: Int) {
    holder.setText("Position: $position")
  }
}

class SampleVH(view: View) : RecyclerView.ViewHolder(view) {
  fun setText(s: String) {
    (itemView as TextView).text = s
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