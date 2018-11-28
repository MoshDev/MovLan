package space.ersan.movlan.ui.feed

import space.ersan.movlan.common.BaseInteractor
import space.ersan.movlan.domain.feed.FeedNowPlayingMoviesUseCase
import space.ersan.movlan.domain.feed.FeedPopularMoviesUseCase
import space.ersan.movlan.domain.feed.FeedUpcomingMoviesUseCase
import javax.inject.Inject

class FeedInteractor @Inject constructor(
  feedNowPlayingMoviesUseCase: FeedNowPlayingMoviesUseCase,
  feedPopularMoviesUseCase: FeedPopularMoviesUseCase,
  feedUpcomingMoviesUseCase: FeedUpcomingMoviesUseCase
) : BaseInteractor,
  FeedNowPlayingMoviesUseCase by feedNowPlayingMoviesUseCase,
  FeedPopularMoviesUseCase by feedPopularMoviesUseCase,
  FeedUpcomingMoviesUseCase by feedUpcomingMoviesUseCase