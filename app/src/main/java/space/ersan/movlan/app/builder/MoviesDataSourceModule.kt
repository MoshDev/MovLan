package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.DefaultMoviesRepository
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.data.source.local.LocalDataSource
import space.ersan.movlan.data.source.local.MoviesDao
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MovieDbApi
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.data.source.remote.RemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.themoviedbapi.TmdbApiClient

@Module
class MoviesDataSourceModule {

  @AppScope
  @Provides
  fun localDataSource(moviesDao: MoviesDao, cor: AppCoroutineDispatchers): LocalDataSource =
    MoviesLocalDataSource(
      moviesDao,
      cor
    )

  @AppScope
  @Provides
  fun remoteDataSource(api: MovieDbApi, cor: AppCoroutineDispatchers): RemoteDataSource =
    MoviesRemoteDataSource(
      api,
      cor
    )

  @AppScope
  @Provides
  fun moviesRepository(
    tmdbApiClient: TmdbApiClient
  ): MoviesRepository = DefaultMoviesRepository(
    tmdbApiClient
  )
}