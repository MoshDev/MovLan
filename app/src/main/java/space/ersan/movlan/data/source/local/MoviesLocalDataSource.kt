package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.source.MovieDetailsResult
import space.ersan.movlan.data.source.MoviesDataSource
import space.ersan.movlan.data.source.MoviesResult
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MoviesLocalDataSource(private val cor: AppCoroutineDispatchers, moviesDao: MoviesDao) : MoviesDataSource {

  override fun getPopularMovies(page: Int, callback: (MoviesResult) -> Unit) {
  }

  override fun getMovieDetails(movieId: Int, callback: (MovieDetailsResult) -> Unit) {
  }
}