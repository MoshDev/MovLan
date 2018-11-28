package space.ersan.movlan.domain.feed

import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.domain.UseCase
import space.ersan.themoviedbapi.model.movie.Movies
import javax.inject.Inject

interface FeedNowPlayingMoviesUseCase : UseCase {
  suspend fun getNowPlayingMovies(): Movies
}

class DefaultFeedNowPlayingMoviesUseCase @Inject constructor(private val repository: MoviesRepository) :
  FeedNowPlayingMoviesUseCase {
  override suspend fun getNowPlayingMovies(): Movies = repository.getNowPlayingMovies(1)
}
