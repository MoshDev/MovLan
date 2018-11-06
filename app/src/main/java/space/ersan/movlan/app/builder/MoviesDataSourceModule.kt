package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.DefaultMoviesRepository
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.data.source.local.*
import space.ersan.movlan.data.source.remote.MovieDbApi
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.data.source.remote.RemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers

@Module
class MoviesDataSourceModule {

  @AppScope
  @Provides
  fun localDataSource(moviesDao: MoviesDao, cor: AppCoroutineDispatchers): LocalDataSource = MoviesLocalDataSource(
      moviesDao,
      cor)


  @AppScope
  @Provides
  fun remoteDataSource(api: MovieDbApi, cor: AppCoroutineDispatchers): RemoteDataSource = MoviesRemoteDataSource(
      api,
      cor)


  @AppScope
  @Provides
  fun moviesRepository(localDataSource: LocalDataSource,
                       remoteDataSource: RemoteDataSource, moviesDbBoundaryCallbackFactory: MoviesDbBoundaryCallbackFactory)
      : MoviesRepository = DefaultMoviesRepository(
      localDataSource,
      remoteDataSource,
      moviesDbBoundaryCallbackFactory)

  @AppScope
  @Provides
  fun moviesDbBoundaryCallbackFactory(): MoviesDbBoundaryCallbackFactory = DefaultMoviesDbBoundaryCallbackFactory()

}