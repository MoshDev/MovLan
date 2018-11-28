package space.ersan.movlan.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.app.builder.Injector
import javax.inject.Inject

abstract class BaseFragment<V : BaseView, VM : ViewModel> : Fragment() {

  @Inject
  protected lateinit var view: V

  @Inject
  protected lateinit var viewModel: VM

  @Inject
  protected lateinit var nativeView: NativeView

  protected abstract fun onInject(
    savedInstanceState: Bundle?,
    injector: Injector
  )

  protected abstract fun startBinding()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onInject(savedInstanceState, Movlan.injector)
    startBinding()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return nativeView.getView()
  }
}