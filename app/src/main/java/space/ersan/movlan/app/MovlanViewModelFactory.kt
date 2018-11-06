package space.ersan.movlan.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.DefaultMovieDetailsViewModel
import space.ersan.movlan.movie.DefaultMovieListingViewModel
import space.ersan.movlan.search.DefaultMovieSearchViewModel
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MovlanViewModelFactory(private val moviesRepository: MoviesRepository, private val cor: AppCoroutineDispatchers) : ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
    when {
      isAssignableFrom(DefaultMovieListingViewModel::class.java) -> {
        DefaultMovieListingViewModel(moviesRepository, cor)
      }
      isAssignableFrom(DefaultMovieDetailsViewModel::class.java) -> {
        DefaultMovieDetailsViewModel(moviesRepository, cor)
      }
      isAssignableFrom(DefaultMovieSearchViewModel::class.java) -> {
        DefaultMovieSearchViewModel(moviesRepository, cor)
      }
      else -> throw IllegalArgumentException("Cannot find ViewModel class for ${modelClass.name}")
    }
  } as T

}