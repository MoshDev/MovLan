package space.ersan.movlan.search

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class MovieSearchPresenter(private val lifecycleOwner: LifecycleOwner, private val view: MovieSearchView, private val viewModel: MovieSearchViewModel) {

  fun onCreate() {
  }

  fun searchFor(query: String) {
    view.setTitle(query)
    viewModel.searchFor(query)
        .observe(lifecycleOwner, Observer { view.setMovies(it) })
    view.observeMovieListClicks(viewModel::showMovieDetails)
  }
}