package space.ersan.movlan.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.ersan.movlan.app.ComponentProvider
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.home.builder.HomeComponent
import javax.inject.Inject

class MovieSearchFragment : Fragment() {

  @Inject
  lateinit var presenterDefault: MovieSearchPresenter

  @Inject
  lateinit var view: NativeView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    @Suppress("UNCHECKED_CAST")
    Movlan.injector.inject(this,
        (requireActivity() as ComponentProvider<HomeComponent>).getComponent())
    presenterDefault.onCreate()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return view.getView()
  }
}