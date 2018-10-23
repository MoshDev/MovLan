package space.ersan.movlan.movie

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MovieListingPresenter(private val lifecycleOwner: LifecycleOwner, private val view: MovieListingView, private val viewModel: MovieListingViewModel) : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreate() {
    viewModel.observeMovies(lifecycleOwner, view::setMovies)
    viewModel.observeNetworkStatus(lifecycleOwner, view::setNetworkStatus)
    view.observeSwipeToRefresh(viewModel::refreshData)
    view.observeMovieListClicks(viewModel::showMovieDetails)
  }

}