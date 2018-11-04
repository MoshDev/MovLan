package space.ersan.movlan.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.utils.Maybe

interface MoviesRepository {
  fun getPopularMoviesPaginated(): LiveData<PagedList<Movie>>
  fun getMovieDetails(movieId: Int): LiveData<Movie>
  fun invalidate()
  fun searchMovies(query: String): LiveData<PagedList<Movie>>
  fun loadPopularMovies(page: Int)
  fun searchMovies(query: String, page: Int, sorting: (Movie) -> Double, callback: (Maybe<MovieList>) -> Unit)
}