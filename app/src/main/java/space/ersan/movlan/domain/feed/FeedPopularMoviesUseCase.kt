package space.ersan.movlan.domain.feed

import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.domain.UseCase
import space.ersan.themoviedbapi.model.movie.Movies
import javax.inject.Inject

interface FeedPopularMoviesUseCase : UseCase {
  suspend fun getPopularMovies(): Movies
}

class DefaultFeedPopularMoviesUseCase @Inject constructor(private val repository: MoviesRepository) :
  FeedPopularMoviesUseCase {
  override suspend fun getPopularMovies(): Movies = repository.getPopularMovies(1)
}