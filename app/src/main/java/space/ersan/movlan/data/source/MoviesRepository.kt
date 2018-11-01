package space.ersan.movlan.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie

interface MoviesRepository {
  fun getPopularMovies(): LiveData<PagedList<Movie>>
  fun getMovieDetails(movieId: Int): LiveData<Movie>
  fun invalidate()
  fun searchMovies(query: String): LiveData<PagedList<Movie>>
}