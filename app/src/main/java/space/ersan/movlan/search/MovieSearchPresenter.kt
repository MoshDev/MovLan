package space.ersan.movlan.search

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class MovieSearchPresenter(private val lifecycleOwner: LifecycleOwner, private val view: MovieSearchView, private val viewModel: MovieSearchViewModel) {

  fun onCreate() {
    view.observeMovieListClicks(viewModel::showMovieDetails)
  }

  fun searchFor(query: String) {
    view.setTitle(query)
    if (!viewModel.isTheSameQuery(query)) {
      viewModel.searchFor(query)
          .observe(lifecycleOwner, Observer { view.setMovies(it) })
    }
  }
}