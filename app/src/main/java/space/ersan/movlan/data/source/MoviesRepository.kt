package space.ersan.movlan.data.source

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MoviesRepository(private val cor: AppCoroutineDispatchers,
                       private val localDataSource: MoviesLocalDataSource,
                       private val remoteDataSource: MoviesRemoteDataSource) {

  fun getPopularMovies(page: Int, callback: (Maybe<MovieList>) -> Unit) {
    launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(page)
      withContext(cor.UI) {
        callback(result)
      }
    }
  }

  fun getGenres(clb: (Maybe<GenreList>) -> Unit) {
    launch(cor.NETWORK) {
      val result = remoteDataSource.getGenres()
      withContext(cor.UI) {
        clb(result)
      }
    }
  }

  fun getMovieDetails(movieId: Int, callback: (Movie) -> Unit) {
  }
}