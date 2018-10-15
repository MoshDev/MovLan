package space.ersan.movlan.data

import androidx.paging.DataSource
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesPagedDataSource
import space.ersan.movlan.data.source.local.MoviesLocalDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MoviesDataSourceFactory(private val cor: AppCoroutineDispatchers,
                              private val localDataSource: MoviesLocalDataSource,
                              private val remoteDataSource: MoviesRemoteDataSource)
  : DataSource.Factory<MoviesPagedDataSource.PageInfo, Movie>() {

  override fun create(): DataSource<MoviesPagedDataSource.PageInfo, Movie> {
    return MoviesPagedDataSource(cor,localDataSource, remoteDataSource)
  }
}