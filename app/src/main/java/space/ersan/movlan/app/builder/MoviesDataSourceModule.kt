package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.data.source.local.MoviesDao
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.data.source.remote.MovieDbApi
import space.ersan.movlan.utils.AppCoroutineDispatchers

@Module
class MoviesDataSourceModule {

  @AppScope
  @Provides
  fun localDataSource(cor: AppCoroutineDispatchers, moviesDao: MoviesDao) =
      MoviesLocalDataSource(cor, moviesDao)


  @AppScope
  @Provides
  fun remoteDataSource(cor: AppCoroutineDispatchers, api: MovieDbApi) =
      MoviesRemoteDataSource(cor, api)


  @AppScope
  @Provides
  fun moviesRepository(localDataSource: MoviesLocalDataSource, remoteDataSource: MoviesRemoteDataSource) =
      MoviesRepository(localDataSource, remoteDataSource)

}