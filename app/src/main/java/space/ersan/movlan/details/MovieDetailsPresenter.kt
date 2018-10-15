package space.ersan.movlan.details

class MovieDetailsPresenter(private val view: MovieDetailsView, private val model: MovieDetailsViewModel) {

  fun onCreate() {

    model.loadMovie(view::setMovie)
  }
}