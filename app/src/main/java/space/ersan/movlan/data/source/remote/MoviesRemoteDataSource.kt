package space.ersan.movlan.data.source.remote

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import space.ersan.movlan.data.source.MovieDetailsResult
import space.ersan.movlan.data.source.MoviesDataSource
import space.ersan.movlan.data.source.MoviesResult
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MoviesRemoteDataSource(private val cor: AppCoroutineDispatchers, private val api: MovieDbApi) : MoviesDataSource {

  override fun getPopularMovies(page: Int, callback: (MoviesResult) -> Unit) {
    launch(cor.NETWORK) {
      val result = try {
        MoviesResult.Success(api.getPopularMovies(page).await())
      } catch (err: Exception) {
        MoviesResult.Error(err)
      }
      withContext(cor.UI) {
        callback(result)
      }
    }
  }

  override fun getMovieDetails(movieId: Int, callback: (MovieDetailsResult) -> Unit) {
    launch(cor.NETWORK) {
      val result = try {
        MovieDetailsResult.Success(api.getMovieDetails(movieId).await())
      } catch (err: Exception) {
        MovieDetailsResult.Error(err)
      }
      withContext(cor.UI) {
        callback(result)
      }
    }
  }
}