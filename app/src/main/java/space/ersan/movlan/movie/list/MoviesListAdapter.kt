package space.ersan.movlan.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import space.ersan.movlan.R
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.image.ImageLoader

class MoviesListAdapter(private val posterLoader: ImageLoader.Poster) :
  PagedListAdapter<Movie, MoviePosterViewHolder>(
    DIFF_CALLBACK
  ) {

  override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
    val movie = getItem(position)
    movie?.let {
      holder.onBind(it)
    }
  }

  var clicksCallback: ((Movie) -> Unit)? = null

  private val innerCallback: (Movie) -> Unit = {
    clicksCallback?.invoke(it)
  }

  public override fun getItem(position: Int): Movie? {
    return super.getItem(position)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return MoviePosterViewHolder(
      posterLoader,
      inflater.inflate(R.layout.view_movies_list_item, parent, false),
      innerCallback
    )
  }

  private companion object {
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
