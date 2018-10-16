package space.ersan.movlan.details

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository

class MovieDetailsViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

  internal var movieId: Int = 0

  fun observeMovie(lifecycleOwner: LifecycleOwner, clb: (Movie) -> Unit) {
    moviesRepository.getMovieDetails(movieId)
        .observe(lifecycleOwner, Observer(clb))
  }

  fun openHomePage(homePage: String) {
    val movlanApp: MovlanApp = getApplication()
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(homePage))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    movlanApp.startActivity(intent)
  }
}