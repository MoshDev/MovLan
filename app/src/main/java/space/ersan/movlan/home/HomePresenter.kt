package space.ersan.movlan.home

import android.os.Bundle

class HomePresenter(private val view: HomeView, private val model: HomeModel) {

  fun onCreate() {

    model.observeMovies {
      view.setMovies(it)
    }

    model.observeLoadingStatus {
      println("Mosh is Loading??? $it")
    }

    view.observeEndlessScroll {
      model.loadNextPage()
    }
    view.observeMovieListClicks {
      println("Mosh $it")
    }
  }

}