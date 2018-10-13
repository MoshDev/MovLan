package space.ersan.movlan.common.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<V : BaseView, P : BasePresenter> : Fragment(), Navigator, LifeCycleAware {

  @Inject
  lateinit var view: V

  @Inject
  lateinit var presenter: P

  abstract fun onInject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onInject()
    presenter.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return view
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDestroy()
  }

  override fun showFragment(fragment: Fragment, tag: String?, addToBackStack: Boolean) {

  }

  override fun popUp() {
    childFragmentManager.popBackStack()
  }

  override fun onVisibilityChangeRequest(visible: Boolean) {
  }

  override fun onPause() {
    super.onPause()
    presenter.onPause()
  }

  override fun onStart() {
    super.onStart()
    presenter.onStart()
  }
}

interface Navigator {
  fun showFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true)
  fun popUp()
}