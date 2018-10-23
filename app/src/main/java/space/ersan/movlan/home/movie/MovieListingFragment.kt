package space.ersan.movlan.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.ersan.movlan.app.Movlan
import javax.inject.Inject

class MovieListingFragment : Fragment() {

  @Inject
  lateinit var presenter: MovieListingPresenter

  @Inject
  lateinit var view: MovieListingView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Movlan.injector.inject(this)
    presenter.onCreate()
  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return view
  }
}