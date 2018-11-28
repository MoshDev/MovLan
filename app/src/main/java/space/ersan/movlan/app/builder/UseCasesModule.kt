package space.ersan.movlan.app.builder

import dagger.Binds
import dagger.Module
import space.ersan.movlan.domain.UseCase
import space.ersan.movlan.domain.feed.DefaultFeedNowPlayingMoviesUseCase
import space.ersan.movlan.domain.feed.DefaultFeedPopularMoviesUseCase
import space.ersan.movlan.domain.feed.DefaultFeedUpcomingMoviesUseCase
import space.ersan.movlan.domain.feed.FeedNowPlayingMoviesUseCase
import space.ersan.movlan.domain.feed.FeedPopularMoviesUseCase
import space.ersan.movlan.domain.feed.FeedUpcomingMoviesUseCase
import space.ersan.movlan.ui.movie.usecase.GetPopularMoviesUseCase

@Module
abstract class UseCasesModule {

  @Binds
  abstract fun providePopularMoviesUseCase(instance: GetPopularMoviesUseCase): UseCase

  @Binds
  abstract fun feedNowPlayingMoviesUseCase(instance: DefaultFeedNowPlayingMoviesUseCase): FeedNowPlayingMoviesUseCase

  @Binds
  abstract fun feedPopularMoviesUseCase(instance: DefaultFeedPopularMoviesUseCase): FeedPopularMoviesUseCase

  @Binds
  abstract fun feedUpcomingMoviesUseCase(instance: DefaultFeedUpcomingMoviesUseCase): FeedUpcomingMoviesUseCase
}