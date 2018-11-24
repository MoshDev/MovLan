package space.ersan.movlan.data.source

import space.ersan.themoviedbapi.TmdbApiClient
import space.ersan.themoviedbapi.model.movie.Movies

class DefaultMoviesRepository(
  private val apiClient: TmdbApiClient
) : MoviesRepository {

  override suspend fun getPopularMovies(page: Int): Movies {
    return apiClient.getPopularMovies(page)
  }
}

interface MoviesRepository {
  suspend fun getPopularMovies(page: Int): Movies
}