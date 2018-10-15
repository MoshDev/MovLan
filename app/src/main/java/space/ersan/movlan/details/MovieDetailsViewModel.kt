package space.ersan.movlan.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository

class MovieDetailsViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

  var movieId: Int = 0

  fun loadMovie(clb: (Movie?) -> Unit) = moviesRepository.getMovieDetails(movieId, clb)

}