package space.ersan.movlan.viewmodel

import androidx.lifecycle.ViewModel

interface ViewModelCreator {
  fun <T : ViewModel> get(): T
  fun <T : ViewModel> isFor(modelClass: Class<T>): Boolean
}