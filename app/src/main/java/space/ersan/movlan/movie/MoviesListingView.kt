package space.ersan.movlan.movie

import android.content.Context
import androidx.paging.PagedList
import space.ersan.movlan.utils.NetworkStatus
import space.ersan.themoviedbapi.model.movie.Movie

interface MoviesListingView {
  fun setMovies(result: PagedList<Movie>)
  fun observeMovieListClicks(clb: (Context, Movie) -> Unit)
  fun observeSwipeToRefresh(clb: () -> Unit)
  fun setNetworkStatus(networkStatus: NetworkStatus)
}