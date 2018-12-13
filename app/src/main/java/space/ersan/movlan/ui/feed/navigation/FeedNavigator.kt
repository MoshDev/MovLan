package space.ersan.movlan.ui.feed.navigation

import space.ersan.movlan.common.Navigator
import space.ersan.themoviedbapi.model.movie.Movie

interface FeedNavigator : Navigator {
  fun showPlayingNow()
  fun showMostPopular()
  fun showUpcoming()
  fun showMovieDetails(movie: Movie)
}