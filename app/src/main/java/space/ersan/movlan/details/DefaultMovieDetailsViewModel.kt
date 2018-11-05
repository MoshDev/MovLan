package space.ersan.movlan.details

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository

class DefaultMovieDetailsViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application), MovieDetailsViewModel {

  override fun getMovieDetails(movieId: Int) = moviesRepository.getMovieDetails(movieId)

  override fun openHomePage(homePage: String?) {
    val movlanApp: MovlanApp = getApplication()
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(homePage))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    movlanApp.startActivity(intent)
  }
}

interface MovieDetailsViewModel {
  fun getMovieDetails(movieId: Int): LiveData<Movie>
  fun openHomePage(homePage: String?)
}
