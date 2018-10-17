package space.ersan.movlan.home

import androidx.lifecycle.LifecycleOwner

class HomePresenter(private val lifecycleOwner: LifecycleOwner, private val view: HomeView, private val viewModel: HomeViewModel) {

  fun onCreate() {

    viewModel.observeMovies(lifecycleOwner, view::setMovies)
    viewModel.observeNetworkStatus(lifecycleOwner, view::setNetworkStatus)
    view.observeSwipeToRefresh(viewModel::refreshData)
    view.observeMovieListClicks(viewModel::showMovieDetails)

  }

}