package space.ersan.movlan.data.source

import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource

class MoviesRepository(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesDataSource {
  override fun getPopularMovies(page: Int, callback: (MoviesResult) -> Unit) {

  }

  override fun getMovieDetails(movieId: Int, callback: (MovieDetailsResult) -> Unit) {
  }
}