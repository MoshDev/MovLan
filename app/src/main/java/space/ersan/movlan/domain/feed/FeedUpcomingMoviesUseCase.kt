package space.ersan.movlan.domain.feed

import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.domain.UseCase
import space.ersan.themoviedbapi.model.movie.Movies
import javax.inject.Inject

interface FeedUpcomingMoviesUseCase : UseCase {
  suspend fun getUpcomingMovies(): Movies
}

class DefaultFeedUpcomingMoviesUseCase @Inject constructor(private val repository: MoviesRepository) :
  FeedUpcomingMoviesUseCase {
  override suspend fun getUpcomingMovies(): Movies = repository.getUpcomingMovies(1)
}