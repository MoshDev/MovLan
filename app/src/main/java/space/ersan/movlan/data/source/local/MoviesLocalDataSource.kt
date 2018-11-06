package space.ersan.movlan.data.source.local

import kotlinx.coroutines.withContext
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.getEnrichedGenres
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MoviesLocalDataSource(private val moviesDao: MoviesDao, private val cor: AppCoroutineDispatchers) : LocalDataSource {

  override suspend fun insertAll(page: Int, movies: List<Movie>) {
    withContext(cor.IO) {
      val genres = getGenres()

      movies.mapIndexed { index: Int, movie: Movie ->
        movie.copy(page = page, indexInListing = (page * 1000000) + index,
            genres = movie.getEnrichedGenres(genres))
      }
          .run { moviesDao.insertMovies(this) }
    }
  }

  override suspend fun getMovies() = withContext(cor.IO) { moviesDao.getMovies() }
  override suspend fun deleteAllMovies() = withContext(cor.IO) { moviesDao.deleteAllMovies() }
  override suspend fun deleteAllMoviesExcept(pageToKeep: Int) = withContext(cor.IO) {
    moviesDao.deleteAllMoviesExcept(
        pageToKeep)
  }

  override suspend fun getGenres() = withContext(cor.IO) { moviesDao.getGenres() }
  override suspend fun insertAllGenres(genres: List<Genre>) = withContext(cor.IO) {
    moviesDao.insertGenres(genres)
  }

  override suspend fun deleteAllGenres() = withContext(cor.IO) { moviesDao.deleteAllGenres() }
  override suspend fun getMovie(movieId: Int) = withContext(cor.IO) { moviesDao.getMovie(movieId) }

}