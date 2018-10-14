package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.Maybe
import space.ersan.movlan.data.source.MoviesDataSource

class MoviesLocalDataSource(moviesDao: MoviesDao) : MoviesDataSource {

  override suspend fun getPopularMovies(page: Int): Maybe<MovieList> = Maybe.None()

  override suspend fun getMovieDetails(movieId: Int): Maybe<Movie> = Maybe.None()

}