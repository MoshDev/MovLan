package space.ersan.movlan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MovlanViewModelFactory @Inject constructor(private val map: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
  : ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    val provider = map[modelClass]
    if (provider != null) {
      return provider.get() as T
    }
    throw IllegalArgumentException("Cannot find ViewModel class for ${modelClass.name}")
  }

}