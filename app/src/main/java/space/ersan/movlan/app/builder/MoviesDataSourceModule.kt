package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.DefaultMoviesRepository
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.data.source.local.LocalDataSource
import space.ersan.movlan.data.source.local.MoviesDao
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallback
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
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
                       remoteDataSource: RemoteDataSource, moviesDbBoundaryCallback: MoviesDbBoundaryCallback,
                       networkStatus: LiveNetworkStatus): MoviesRepository = DefaultMoviesRepository(
      cor,
      localDataSource,
      remoteDataSource,
      moviesDbBoundaryCallback,
      networkStatus)

  @AppScope
  @Provides
  fun moviesDbBoundaryCallback(cor: AppCoroutineDispatchers,
                               localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource,
                               networkStatus: LiveNetworkStatus) = MoviesDbBoundaryCallback(cor,
      remoteDataSource,
      localDataSource, networkStatus)

  @AppScope
  @Provides
  fun movieDataSourceNetworkStatus(): LiveNetworkStatus {
    return LiveNetworkStatus()
  }


}