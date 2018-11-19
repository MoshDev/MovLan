package space.ersan.movlan.data.source.remote

import kotlinx.coroutines.withContext
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.Maybe

class MoviesRemoteDataSource(
  private val api: MovieDbApi,
  private val cor: AppCoroutineDispatchers
) : RemoteDataSource {

  override suspend fun getPopularMovies(page: Int): Maybe<MovieList> = withContext(cor.NETWORK) {
    try {
      Maybe.Some(api.getPopularMovies(page).await())
    } catch (err: Exception) {
      Maybe.Error<MovieList>(err)
    }
  }

  override suspend fun getMovieDetails(movieId: Int): Maybe<Movie> = withContext(cor.NETWORK) {
    try {
      Maybe.Some(api.getMovieDetails(movieId).await())
    } catch (err: Exception) {
      Maybe.Error<Movie>(err)
    }
  }

  override suspend fun getGenres(): Maybe<GenreList> = withContext(cor.NETWORK) {
    try {
      Maybe.Some(api.getGenres().await())
    } catch (err: Exception) {
      Maybe.Error<GenreList>(err)
    }
  }

  override suspend fun search(query: String, page: Int): Maybe<MovieList> =
    withContext(cor.NETWORK) {
      try {
        Maybe.Some(api.search(query, page).await())
      } catch (err: Exception) {
        Maybe.Error<MovieList>(err)
      }
    }
}