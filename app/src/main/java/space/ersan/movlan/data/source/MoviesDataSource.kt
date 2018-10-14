package space.ersan.movlan.data.source

import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList

interface MoviesDataSource {

  suspend fun getPopularMovies(page: Int = 1): Maybe<MovieList>

  suspend fun getMovieDetails(movieId: Int): Maybe<Movie>

}

sealed class Maybe<T> {
  class Some<T>(val value: T) : Maybe<T>()
  class Error<T>(val error: Exception) : Maybe<T>()
  class None<T> : Maybe<T>()
}