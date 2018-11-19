package space.ersan.movlan.movie.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.ersan.movlan.R
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.toYear
import space.ersan.movlan.image.ImageLoader

class MoviePosterViewHolder(
  private val posterLoader: ImageLoader.Poster,
  view: View,
  private val callback: (Movie) -> Unit
) : RecyclerView.ViewHolder(view) {

  private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
  private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
  private val releaseYearTextView: TextView = itemView.findViewById(R.id.yearTextView)
  private val ratingTextView: TextView = itemView.findViewById(R.id.movieRatingTextView)
  private val genresTextView: TextView = itemView.findViewById(R.id.genresTextView)

  fun onBind(item: Movie) {
    itemView.setOnClickListener { callback(item) }
    posterLoader.loadImage(thumbnailImageView, item)
    titleTextView.text = item.title
    releaseYearTextView.text = item.releaseDate?.toYear() ?: ""
    ratingTextView.text = item.voteAverage?.toString() ?: ":| "
    genresTextView.text = item.genres?.joinToString(" | ") { genre ->
      genre.name ?: ""
    }
  }
}
