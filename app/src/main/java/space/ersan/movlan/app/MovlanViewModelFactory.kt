package space.ersan.movlan.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.DefaultMovieDetailsViewModel
import space.ersan.movlan.movie.DefaultMovieListingViewModel
import space.ersan.movlan.search.DefaultMovieSearchViewModel
import space.ersan.movlan.utils.LiveNetworkStatus

class MovlanViewModelFactory(private val application: Application, private val moviesRepository: MoviesRepository,
                             private val networkStatus: LiveNetworkStatus) : ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
    when {
      isAssignableFrom(DefaultMovieListingViewModel::class.java) -> {
        DefaultMovieListingViewModel(application,
            moviesRepository,
            networkStatus)
      }
      isAssignableFrom(DefaultMovieDetailsViewModel::class.java) -> {
        DefaultMovieDetailsViewModel(application,
            moviesRepository)
      }
      isAssignableFrom(DefaultMovieSearchViewModel::class.java) -> {
        DefaultMovieSearchViewModel(application,
            moviesRepository)
      }
      else -> throw IllegalArgumentException("Cannot find ViewModel class for ${modelClass.name}")
    }
  } as T

}