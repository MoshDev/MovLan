package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.utils.Maybe

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

  fun insertAll(page: Int, movies: List<Movie>) {
    val listed = movies.mapIndexed { index: Int, movie: Movie ->
      movie.page = page
      movie.indexInListing = index
      return@mapIndexed movie
    }
    moviesDao.insert(listed)
  }

  suspend fun getPopularMovies(page: Int): Maybe<MovieList> = Maybe.None()

  suspend fun getMovieDetails(movieId: Int): Maybe<Movie> = Maybe.None()

  suspend fun getGenres(): Maybe<GenreList> = Maybe.None()
}