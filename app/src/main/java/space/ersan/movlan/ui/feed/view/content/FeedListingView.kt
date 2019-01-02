package space.ersan.movlan.ui.feed.view.content

import android.app.Application
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import space.ersan.movlan.R
import space.ersan.movlan.image.ImageLoader
import space.ersan.themoviedbapi.model.movie.Movies

class FeedListingView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private val adapter =
    FeedMoviesAdapter(ImageLoader.Poster(context.applicationContext as Application))

  init {
    View.inflate(context, R.layout.view_feed_listing, this)

    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
      layoutManager = StaggeredGridLayoutManager(
        context.resources.getInteger(R.integer.listing_movies_span),
        StaggeredGridLayoutManager.VERTICAL
      )
      adapter = this@FeedListingView.adapter
    }
  }

  fun setMovies(popular: Movies, upcoming: Movies) {
    adapter.add(resources.getString(R.string.popular))
    adapter.add(popular)
    adapter.add(resources.getString(R.string.upcoming))
    adapter.add(upcoming)
    adapter.notifyDataSetChanged()
  }
}