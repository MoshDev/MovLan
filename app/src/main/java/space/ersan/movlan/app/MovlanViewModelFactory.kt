package space.ersan.movlan.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.home.HomeViewModel

class MovlanViewModelFactory(private val application: Application, private val moviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
    when {
      isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(application, moviesRepository)
      else -> throw IllegalArgumentException("Cannot find ViewModel class for ${modelClass.name}")
    }
  } as T

}