package space.ersan.movlan.ui.feed.view

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import space.ersan.movlan.R
import space.ersan.movlan.common.BaseView
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.ui.feed.FeedMovies
import space.ersan.movlan.utils.NetworkStatus
import space.ersan.themoviedbapi.model.movie.Movie

class FeedView(context: Context) : BaseView, NativeView {

  private val view: View = LayoutInflater.from(context).inflate(R.layout.view_feed, null)
  private val feedNowPlayingView = view.findViewById<FeedNowPlayingView>(R.id.feedNowPlayingView)
  private val adapter = SampleAdapter(ImageLoader.Poster(context.applicationContext as Application))

  init {

    val recyclerView = view.findViewById<RecyclerView>(R.id.bottomSheet).apply {
      layoutManager = StaggeredGridLayoutManager(
        context.resources.getInteger(R.integer.listing_movies_span),
        StaggeredGridLayoutManager.VERTICAL
      )
      adapter = this@FeedView.adapter
    }


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
    adapter.list.addAll(feedMovies.popular)
    adapter.list.addAll(feedMovies.upcoming)
    adapter.notifyDataSetChanged()
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

private class SampleAdapter(private val imageLoader: ImageLoader) :
  RecyclerView.Adapter<SampleVH>() {

  val list = mutableListOf<Movie>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleVH {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_movies_list_item, parent, false)
    return SampleVH(view, imageLoader)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: SampleVH, position: Int) {
    holder.bind(list[position])
  }
}

class SampleVH(view: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(view) {
  private val imageView = itemView.findViewById<ImageView>(R.id.thumbnailImageView)

  fun bind(movie: Movie) {
    imageLoader.loadImage(imageView, movie)
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