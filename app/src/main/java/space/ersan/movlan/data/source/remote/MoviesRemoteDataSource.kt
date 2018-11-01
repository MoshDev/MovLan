package space.ersan.movlan.data.source.remote

import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.utils.Maybe

class MoviesRemoteDataSource(private val api: MovieDbApi) : RemoteDataSource {

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

  override suspend fun search(query: String, page: Int): Maybe<MovieList> = try {
    Maybe.Some(api.search(query, page).await())
  } catch (err: Exception) {
    Maybe.Error(err)
  }

}