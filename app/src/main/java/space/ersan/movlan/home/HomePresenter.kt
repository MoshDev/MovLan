package space.ersan.movlan.home

import space.ersan.movlan.utils.NetworkStatus

class HomePresenter(private val view: HomeView, private val model: HomeModel) {

  fun onCreate() {

    model.observeMovies {
      view.setMovies(it)
    }

    model.observeNetworkStatus(view::setNetworkStatus)

    view.observeMovieListClicks {
      println("Mosh $it")
    }

    view.observeSwipeToRefresh {
      model.viewModel()
          .refreshData()
       false
    }
  }

}