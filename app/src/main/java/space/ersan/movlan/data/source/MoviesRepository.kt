package space.ersan.movlan.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.Maybe

interface MoviesRepository {
  suspend fun getPopularMoviesPaginated(networkStatus: LiveNetworkStatus): LiveData<PagedList<Movie>>
  suspend fun getMovieDetails(movieId: Int, clb: (Movie) -> Unit)
  suspend fun invalidate(networkStatus: LiveNetworkStatus)
  suspend fun searchMovies(query: String): LiveData<PagedList<Movie>>
  suspend fun loadPopularMovies(page: Int, networkStatus: LiveNetworkStatus)
  suspend fun searchMovies(query: String, page: Int, sorting: (Movie) -> Double, callback: (Maybe<MovieList>) -> Unit)
}