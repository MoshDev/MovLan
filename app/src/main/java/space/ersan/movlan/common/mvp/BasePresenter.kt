package space.ersan.movlan.common.mvp

import android.os.Bundle


abstract class BasePresenter {

  abstract fun onCreate(savedInstanceState: Bundle?)
  abstract fun onDestroy()

  open fun onStart() {}

  open fun onPause(){}

}