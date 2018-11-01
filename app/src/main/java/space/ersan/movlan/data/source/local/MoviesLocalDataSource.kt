package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.getEnrichedGenres

class MoviesLocalDataSource(private val moviesDao: MoviesDao) : LocalDataSource {

  override fun insertAll(page: Int, movies: List<Movie>) {

    val genres = getGenres()

    movies.mapIndexed { index: Int, movie: Movie ->
      movie.copy(page = page, indexInListing = (page * 1000000) + index,
          genres = movie.getEnrichedGenres(genres))
    }
        .run { moviesDao.insertMovies(this) }
  }

  override fun getMovies() = moviesDao.getMovies()
  override fun deleteAllMovies() = moviesDao.deleteAllMovies()
  override fun deleteAllMoviesExcept(pageToKeep: Int) = moviesDao.deleteAllMoviesExcept(pageToKeep)
  override fun getGenres() = moviesDao.getGenres()
  override fun insertAllGenres(genres: List<Genre>) = moviesDao.insertGenres(genres)
  override fun deleteAllGenres() = moviesDao.deleteAllGenres()
  override fun getMovie(movieId: Int) = moviesDao.getMovie(movieId)

}