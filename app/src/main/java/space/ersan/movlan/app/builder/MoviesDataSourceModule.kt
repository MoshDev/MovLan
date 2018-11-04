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
import space.ersan.movlan.utils.LiveNetworkStatus

@Module
class MoviesDataSourceModule {

  @AppScope
  @Provides
  fun localDataSource(moviesDao: MoviesDao): LocalDataSource = MoviesLocalDataSource(moviesDao)


  @AppScope
  @Provides
  fun remoteDataSource(api: MovieDbApi): RemoteDataSource = MoviesRemoteDataSource(api)


  @AppScope
  @Provides
  fun moviesRepository(cor: AppCoroutineDispatchers, localDataSource: LocalDataSource,
                       remoteDataSource: RemoteDataSource, moviesDbBoundaryCallbackFactory: MoviesDbBoundaryCallbackFactory,
                       networkStatus: LiveNetworkStatus): MoviesRepository = DefaultMoviesRepository(
      cor,
      localDataSource,
      remoteDataSource,
      moviesDbBoundaryCallbackFactory,
      networkStatus)

  @AppScope
  @Provides
  fun moviesDbBoundaryCallbackFactory(): MoviesDbBoundaryCallbackFactory = DefaultMoviesDbBoundaryCallbackFactory()

  @AppScope
  @Provides
  fun movieDataSourceNetworkStatus(): LiveNetworkStatus {
    return LiveNetworkStatus()
  }


}