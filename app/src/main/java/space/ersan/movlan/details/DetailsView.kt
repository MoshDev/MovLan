package space.ersan.movlan.details

import space.ersan.movlan.data.model.Movie

interface DetailsView {
  fun setMovie(movie: Movie)
  fun observeHomePageButtonClicks(clb: (String?) -> Unit)
}