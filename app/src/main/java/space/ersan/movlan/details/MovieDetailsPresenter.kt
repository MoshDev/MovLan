package space.ersan.movlan.details

import androidx.lifecycle.LifecycleOwner

class MovieDetailsBinder(private val lifecycleOwner: LifecycleOwner, private val view: DetailsView, private val viewModel: MovieDetailsViewModel) {

  fun onCreate() {

    viewModel.observeMovie(lifecycleOwner, view::setMovie)
    view.observeHomePageButtonClicks {
      it?.also { homepage -> viewModel.openHomePage(homepage) }
    }

  }
}