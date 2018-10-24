package space.ersan.movlan.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsViewModel
import space.ersan.movlan.movie.MovieListingViewModel
import space.ersan.movlan.search.MovieSearchViewModel
import space.ersan.movlan.utils.LiveNetworkStatus

class MovlanViewModelFactory(private val application: Application, private val moviesRepository: MoviesRepository,
                             private val networkStatus: LiveNetworkStatus) : ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
    when {
      isAssignableFrom(MovieListingViewModel::class.java) -> {
        MovieListingViewModel(application,
            moviesRepository,
            networkStatus)
      }
      isAssignableFrom(MovieDetailsViewModel::class.java) -> {
        MovieDetailsViewModel(application,
            moviesRepository)
      }
      isAssignableFrom(MovieSearchViewModel::class.java) -> {
        MovieSearchViewModel(application,
            moviesRepository)
      }
      else -> throw IllegalArgumentException("Cannot find ViewModel class for ${modelClass.name}")
    }
  } as T

}