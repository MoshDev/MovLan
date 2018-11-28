package space.ersan.movlan.ui.details

import space.ersan.themoviedbapi.model.movie.Movie

interface MovieDetailsView {
  fun setMovie(movie: Movie)
  fun observeHomePageButtonClicks(clb: (String?) -> Unit)
}