package space.ersan.movlan.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import space.ersan.movlan.R
import space.ersan.movlan.common.view.BaseAdapter
import space.ersan.movlan.common.view.BaseViewHolder
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.toDeviceDate
import space.ersan.movlan.ext.toYear
import space.ersan.movlan.image.ImageLoader

class MoviesListAdapter(private val thumbnailLoader: ImageLoader.Thumbnail) : PagedListAdapter<Movie, MoviesListItemViewHolder>(
    DIFF_CALLBACK) {

  override fun onBindViewHolder(holder: MoviesListItemViewHolder, position: Int) {
    val movie = getItem(position)
    movie?.let {
      holder.onBind(it)
    }
  }

  private val innerCallback: (Movie) -> Unit = {
  }

  public override fun getItem(position: Int): Movie? {
    return super.getItem(position)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return MoviesListItemViewHolder(thumbnailLoader,
        inflater.inflate(R.layout.view_movies_list_item, parent, false),
        innerCallback)
  }


  companion object {
    private val DIFF_CALLBACK = object :
        DiffUtil.ItemCallback<Movie>() {

      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
      }
    }
  }
}

class MoviesListItemViewHolder(private val thumbnailLoader: ImageLoader.Thumbnail, view: View, callback: ((Movie) -> Unit)?) : BaseViewHolder<Movie>(
    view,
    callback) {

  private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
  private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
  private val releaseYearTextView: TextView = itemView.findViewById(R.id.yearTextView)
  private val ratingTextView: TextView = itemView.findViewById(R.id.movieRatingTextView)
  private val genresTextView: TextView = itemView.findViewById(R.id.genresTextView)

  override fun onBind(item: Movie) {
    thumbnailLoader.loadImage(thumbnailImageView, item)
    titleTextView.text = item.title
    releaseYearTextView.text = item.releaseDate?.toYear() ?: ""
    ratingTextView.text = item.voteAverage?.toString() ?: ":| "
    genresTextView.text = item.genres?.joinToString(" | ") { genre ->
      genre.name ?: ""
    }
  }

}
