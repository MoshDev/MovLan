package space.ersan.movlan.home.movie

import androidx.lifecycle.LifecycleOwner

class MovieListingPresenter(private val lifecycleOwner: LifecycleOwner, private val view: MovieListingView, private val viewModel: MovieListingViewModel) {

  fun onCreate() {

    viewModel.observeMovies(lifecycleOwner, view::setMovies)
    viewModel.observeNetworkStatus(lifecycleOwner, view::setNetworkStatus)
    view.observeSwipeToRefresh(viewModel::refreshData)
    view.observeMovieListClicks(viewModel::showMovieDetails)

  }

}