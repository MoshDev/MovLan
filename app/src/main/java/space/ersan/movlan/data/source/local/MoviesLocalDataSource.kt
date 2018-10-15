package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.model.Movie

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

  fun insertAll(page: Int, movies: List<Movie>) = movies.mapIndexed { index: Int, movie: Movie ->
    movie.page = page
    movie.indexInListing = (page * 1000000) + index
    return@mapIndexed movie
  }.run { moviesDao.insert(this) }
//    moviesDao.insert(listed)

  fun getPopularMovies(page: Int) = moviesDao.getMovies()

  fun deleteAll() = moviesDao.deleteAll()

  fun deleteAllExcept(pageToKeep: Int) = moviesDao.deleteAllExcept(pageToKeep)
}