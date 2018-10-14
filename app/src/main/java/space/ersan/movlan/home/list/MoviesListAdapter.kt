package space.ersan.movlan.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import space.ersan.movlan.R
import space.ersan.movlan.common.view.BaseAdapter
import space.ersan.movlan.common.view.BaseViewHolder
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.toDeviceDate
import space.ersan.movlan.image.ImageLoader

class MoviesListAdapter(private val thumbnailLoader: ImageLoader.Thumbnail, callback: ((Movie) -> Unit)?) : BaseAdapter<Movie, MoviesListItemViewHolder>(
    callback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return MoviesListItemViewHolder(thumbnailLoader,
        inflater.inflate(R.layout.view_movies_list_item, parent, false),
        callback)
  }
}

class MoviesListItemViewHolder(private val thumbnailLoader: ImageLoader.Thumbnail, view: View, callback: ((Movie) -> Unit)?) : BaseViewHolder<Movie>(
    view,
    callback) {

  private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
  private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
  private val releaseYearTextView: TextView = itemView.findViewById(R.id.yearTextView)

  override fun onBind(item: Movie) {
    thumbnailLoader.loadImage(thumbnailImageView, item)
    titleTextView.text = item.title
    releaseYearTextView.text = item.releaseDate?.toDeviceDate(context) ?: ""
  }
}