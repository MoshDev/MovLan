package space.ersan.movlan.data.source

import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList

interface MoviesDataSource {

  fun getPopularMovies(page: Int = 1, callback: (MoviesResult) -> Unit)

  fun getMovieDetails(movieId: Int, callback: (MovieDetailsResult) -> Unit)

}

sealed class MoviesResult {
  class Success(val result: MovieList) : MoviesResult()
  class Error(val error: Exception) : MoviesResult()
}

sealed class MovieDetailsResult {
  class Success(val result: Movie) : MovieDetailsResult()
  class Error(val error: Exception) : MovieDetailsResult()
}