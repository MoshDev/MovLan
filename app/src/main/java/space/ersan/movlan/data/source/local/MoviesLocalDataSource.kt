package space.ersan.movlan.data.source.local

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import space.ersan.movlan.data.model.Movie

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

  fun insertAll(page: Int, movies: List<Movie>) {
    println("Mosh insertAll page= $page & list: ${movies.size}")

    val listed = movies.mapIndexed { index: Int, movie: Movie ->
      movie.page = page
      movie.indexInListing = index
      return@mapIndexed movie
    }
    moviesDao.insert(listed)
  }

  fun getPopularMovies(page: Int) = moviesDao.getMoviesByPage(page)

  fun deleteAll() {
    println("Mosh deleteAll")
    moviesDao.deleteAll()
  }
}