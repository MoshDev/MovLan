package space.ersan.movlan.data

import androidx.paging.DataSource
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesPagedDataSource
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers

class MoviesDataSourceFactory(private val cor: AppCoroutineDispatchers, private val remoteDataSource: MoviesRemoteDataSource)
  : DataSource.Factory<Int, Movie>() {

  override fun create(): DataSource<Int, Movie> {
    return MoviesPagedDataSource(cor, remoteDataSource)
  }
}