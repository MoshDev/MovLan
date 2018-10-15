package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.getEnrichedGenres

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

  fun insertAll(page: Int, movies: List<Movie>) {

    val genres = getGenres()

    movies.mapIndexed { index: Int, movie: Movie ->
      movie.copy(page = page, indexInListing = (page * 1000000) + index,
          genres = movie.getEnrichedGenres(genres))
    }
        .run { moviesDao.insertMovies(this) }
  }

  fun getMovies() = moviesDao.getMovies()
  fun deleteAllMovies() = moviesDao.deleteAllMovies()
  fun deleteAllMoviesExcept(pageToKeep: Int) = moviesDao.deleteAllMoviesExcept(pageToKeep)

  fun getGenres() = moviesDao.getGenres()
  fun insertAllGenres(genres: List<Genre>) = moviesDao.insertGenres(genres)
  fun deleteAllGenres() = moviesDao.deleteAllGenres()

}