package space.ersan.movlan.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.LiveNetworkStatus

class HomeViewModel(application: Application, private val moviesRepository: MoviesRepository,
                    val networkStatus: LiveNetworkStatus) : AndroidViewModel(
    application) {

  val movies = moviesRepository.getPopularMovies()

  init {
    refreshData()
  }

  fun refreshData() {
    moviesRepository.invalidate()
  }

}