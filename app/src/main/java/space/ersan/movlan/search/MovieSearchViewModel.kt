package space.ersan.movlan.search

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity

class MovieSearchViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

  fun searchFor(query: String) = moviesRepository.searchMovies(query)

  fun showMovieDetails(context: Context, movie: Movie) {
    context.startActivity(MovieDetailsActivity.intentFor(context, movie.id))
  }
}