package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.data.source.local.MoviesDao
import space.ersan.movlan.data.source.local.MoviesDbBoundaryCallback
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.data.source.remote.MovieDbApi
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus

@Module
class MoviesDataSourceModule {

  @AppScope
  @Provides
  fun localDataSource(moviesDao: MoviesDao) = MoviesLocalDataSource(moviesDao)


  @AppScope
  @Provides
  fun remoteDataSource(api: MovieDbApi) = MoviesRemoteDataSource(api)


  @AppScope
  @Provides
  fun moviesRepository(cor: AppCoroutineDispatchers, localDataSource: MoviesLocalDataSource,
                       remoteDataSource: MoviesRemoteDataSource, moviesDbBoundaryCallback: MoviesDbBoundaryCallback, networkStatus: LiveNetworkStatus) =
      MoviesRepository(cor,
          localDataSource,
          remoteDataSource,
          moviesDbBoundaryCallback,
          networkStatus)

  @AppScope
  @Provides
  fun moviesDbBoundaryCallback(cor: AppCoroutineDispatchers, localDataSource: MoviesLocalDataSource,
                               remoteDataSource: MoviesRemoteDataSource, networkStatus: LiveNetworkStatus) =
      MoviesDbBoundaryCallback(cor,
          remoteDataSource,
          localDataSource, networkStatus)

  @AppScope
  @Provides
  fun movieDataSourceNetworkStatus(): LiveNetworkStatus {
    return LiveNetworkStatus()
  }


}