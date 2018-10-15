package space.ersan.movlan.home

class HomePresenter(private val view: HomeView, private val model: HomeModel) {

  fun onCreate() {

    model.observeMovies {
      view.setMovies(it)
    }

    model.observeLoadingStatus {
      println("Mosh is Loading??? $it")
    }

    view.observeMovieListClicks {
      println("Mosh $it")
    }

    view.observeSwipeToRefresh{
      model.viewModel().refreshData()
      view.setRefreshInficator(false)
    }
  }

}