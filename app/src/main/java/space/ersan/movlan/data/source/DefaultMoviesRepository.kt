package space.ersan.movlan.data.source

import space.ersan.themoviedbapi.TmdbApiClient
import space.ersan.themoviedbapi.model.movie.Movies

class DefaultMoviesRepository(
  private val apiClient: TmdbApiClient
) : MoviesRepository {

  override suspend fun getNowPlayingMovies(page: Int): Movies {
    return apiClient.getNowPlayingMovies(page)
  }

  override suspend fun getPopularMovies(page: Int): Movies {
    return apiClient.getPopularMovies(page)
  }

  override suspend fun getUpcomingMovies(page: Int): Movies {
    return apiClient.getUpcomingMovies(page)
  }
}

interface MoviesRepository {
  suspend fun getPopularMovies(page: Int): Movies
  suspend fun getNowPlayingMovies(page: Int): Movies
  suspend fun getUpcomingMovies(page: Int): Movies
}