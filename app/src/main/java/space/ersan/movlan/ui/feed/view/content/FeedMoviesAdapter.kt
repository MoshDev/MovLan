package space.ersan.movlan.ui.feed.view.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import space.ersan.movlan.R
import space.ersan.movlan.image.ImageLoader
import space.ersan.themoviedbapi.model.movie.Movie
import space.ersan.themoviedbapi.model.movie.Movies

class FeedMoviesAdapter(private val imageLoader: ImageLoader) :
  RecyclerView.Adapter<BaseViewHolder<ItemHolder>>() {

  private val items = mutableListOf<ItemHolder>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = when (viewType) {
    ItemHolder.TYPE_MOVIE -> createMovieViewHolder(parent)
    ItemHolder.TYPE_GROUP -> createGroupViewHolder(parent)
    else -> throw IllegalArgumentException()
  } as BaseViewHolder<ItemHolder>

  private fun createGroupViewHolder(parent: ViewGroup): BaseViewHolder<ItemHolder.GroupHolder> {
    val view = LayoutInflater.from(parent.context)
      .inflate(android.R.layout.simple_list_item_1, parent, false)
    view.updateLayoutParams<StaggeredGridLayoutManager.LayoutParams> {
      this.isFullSpan = true
    }
    return GroupViewHolder(view)
  }

  private fun createMovieViewHolder(parent: ViewGroup): BaseViewHolder<ItemHolder.MovieHolder> {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_movies_list_item, parent, false)
    return MovieViewHolder(view, imageLoader)
  }

  override fun onBindViewHolder(holder: BaseViewHolder<ItemHolder>, position: Int) =
    holder.bind(items[position])

  override fun getItemCount(): Int = items.size
  override fun getItemViewType(position: Int): Int = items[position].type
  fun add(movies: Movies) = items.addAll(movies.map { ItemHolder.MovieHolder(it) })
  fun add(group: String) = items.add(ItemHolder.GroupHolder(group))
}

abstract class BaseViewHolder<ITEM : ItemHolder>(view: View) :
  RecyclerView.ViewHolder(view) {
  abstract fun bind(item: ITEM)
}

class MovieViewHolder(view: View, private val imageLoader: ImageLoader) :
  BaseViewHolder<ItemHolder.MovieHolder>(view) {

  private val imageView = itemView.findViewById<ImageView>(R.id.thumbnailImageView)

  override fun bind(item: ItemHolder.MovieHolder) {
    imageLoader.loadImage(imageView, item.movie)
  }
}

class GroupViewHolder(view: View) :
  BaseViewHolder<ItemHolder.GroupHolder>(view) {

  override fun bind(item: ItemHolder.GroupHolder) {
    (itemView as TextView).text = item.title
  }
}

sealed class ItemHolder(val type: Int) {
  class MovieHolder(val movie: Movie) : ItemHolder(type = TYPE_MOVIE)
  class GroupHolder(val title: CharSequence) : ItemHolder(type = TYPE_GROUP)

  companion object {
    const val TYPE_MOVIE = 1
    const val TYPE_GROUP = 2
  }
}