package space.ersan.movlan.movie

import android.content.Context
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.utils.NetworkStatus

interface MoviesListingView {
  fun setMovies(result: PagedList<Movie>)
  fun observeMovieListClicks(clb: (Context, Movie) -> Unit)
  fun observeSwipeToRefresh(clb: () -> Unit)
  fun setNetworkStatus(networkStatus: NetworkStatus)
}