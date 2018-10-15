package space.ersan.movlan.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.home.HomeViewModel
import space.ersan.movlan.utils.LiveNetworkStatus

class MovlanViewModelFactory(private val application: Application, private val moviesRepository: MoviesRepository,
                             private val networkStatus: LiveNetworkStatus) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
    when {
      isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(application,
          moviesRepository,
          networkStatus)
      else -> throw IllegalArgumentException("Cannot find ViewModel class for ${modelClass.name}")
    }
  } as T

}