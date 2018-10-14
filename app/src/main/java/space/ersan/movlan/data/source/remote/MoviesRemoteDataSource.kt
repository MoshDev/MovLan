package space.ersan.movlan.data.source.remote

import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.Maybe
import space.ersan.movlan.data.source.MoviesDataSource

class MoviesRemoteDataSource(private val api: MovieDbApi) : MoviesDataSource {

  override suspend fun getPopularMovies(page: Int): Maybe<MovieList> = try {
    Maybe.Some(api.getPopularMovies(page).await())
  } catch (err: Exception) {
    Maybe.Error(err)
  }

  override suspend fun getMovieDetails(movieId: Int): Maybe<Movie> = try {
    Maybe.Some(api.getMovieDetails(movieId).await())
  } catch (err: Exception) {
    Maybe.Error(err)
  }

  override suspend fun getGenres(): Maybe<GenreList> = try {
    Maybe.Some(api.getGenres().await())
  } catch (err: Exception) {
    Maybe.Error(err)
  }

}