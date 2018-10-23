package space.ersan.movlan.search

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class MovieSearchPresenter(private val lifecycleOwner: LifecycleOwner,
                           private val view: MovieSearchView,
                           private val viewModel: MovieSearchViewModel) {

  fun onCreate() {
    view.setSearchQueryText(viewModel.searchTextQuery)
    view.observeMovieListClicks(viewModel::showMovieDetails)
    viewModel.getSearchData()
        .observe(lifecycleOwner, Observer {
          view.setMovies(it)
        })
    view.observeSearchQuery(viewModel::searchMovies)
  }
}