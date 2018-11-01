package space.ersan.movlan.movie

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MovieListingPresenter(private val lifecycleOwner: LifecycleOwner, private val viewMovies: MoviesListingView, private val viewModel: MovieListingViewModel) : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreate() {
    viewModel.observeMovies(lifecycleOwner, viewMovies::setMovies)
    viewModel.observeNetworkStatus(lifecycleOwner, viewMovies::setNetworkStatus)
    viewMovies.observeSwipeToRefresh(viewModel::refreshData)
    viewMovies.observeMovieListClicks(viewModel::showMovieDetails)
  }

}