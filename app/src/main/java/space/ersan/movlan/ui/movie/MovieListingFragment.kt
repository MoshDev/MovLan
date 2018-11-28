package space.ersan.movlan.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.ersan.movlan.app.ComponentProvider
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.ui.home.builder.HomeComponent
import javax.inject.Inject

class MovieListingFragment : Fragment() {

  @Inject
  lateinit var nativeView: NativeView

  @Inject
  lateinit var view: MoviesListingView

  @Inject
  lateinit var viewModelDefault: MovieListingViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    @Suppress("UNCHECKED_CAST")
    Movlan.injector.inject(
      this,
      (requireActivity() as ComponentProvider<HomeComponent>).getComponent()
    )

    viewModelDefault.getMovies().observe({ lifecycle }, view::setMovies)
    viewModelDefault.getNetworkStatus().observe({ lifecycle }, view::setNetworkStatus)
    view.observeSwipeToRefresh(viewModelDefault::refreshMovies)
    view.observeMovieListClicks(viewModelDefault::showMovieDetails)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return nativeView.getView()
  }
}