package space.ersan.movlan.ui.feed.view.content

import android.app.Application
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import space.ersan.movlan.R
import space.ersan.movlan.image.ImageLoader
import space.ersan.themoviedbapi.model.movie.Movie

class FeedNowPlayingView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private val imageLoader = ImageLoader.Poster(context.applicationContext as Application)
  private val adapter = NowPlayingAdapter(imageLoader)
  private var originalHeight = 0

  init {
    View.inflate(context, R.layout.view_feed_now_playing, this)
    doOnLayout {
      originalHeight = it.height
    }
  }

  private val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
    //    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    adapter = this@FeedNowPlayingView.adapter
    PagerSnapHelper().attachToRecyclerView(this)
  }

  fun setNowPlayingMovies(movies: List<Movie>) {
    adapter.list.addAll(movies)
    adapter.notifyDataSetChanged()
  }

  fun onSlide(bottomSheet: BottomSheetBehavior<*>, slideOffset: Float) {
    updateLayoutParams {
      height = if (slideOffset < 0) {
        originalHeight + (((slideOffset * -1f) * bottomSheet.peekHeight).toInt())
      } else {
        (originalHeight * (1f - slideOffset)).toInt()
      }
      recyclerView.requestLayout()
    }
//    recyclerView.scaleX = Math.max(1f, 1f + (slideOffset / 1.5f))
//    recyclerView.scaleY = Math.max(1f, 1f + (slideOffset / 1.5f))
  }
}

private class NowPlayingAdapter(private val imageLoader: ImageLoader) :
  RecyclerView.Adapter<NowPlayingViewHolder>() {

  val list = mutableListOf<Movie>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_feed_now_playing_item, parent, false)
    return NowPlayingViewHolder(view, imageLoader)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
    holder.onBind(list[position])
  }
}

private class NowPlayingViewHolder(itemView: View, private val imageLoader: ImageLoader) :
  RecyclerView.ViewHolder(itemView) {
  private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

  fun onBind(movie: Movie) {
    imageLoader.loadImage(imageView, movie)
  }
}