package space.ersan.movlan.data.source.remote.search

import androidx.paging.DataSource
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.remote.MoviesRemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers

class SearchDataSourceFactory(private val cor: AppCoroutineDispatchers,
                              private val remoteDataSource: MoviesRemoteDataSource,
                              private val query: String) : DataSource.Factory<SearchQuery, Movie>() {
  override fun create(): DataSource<SearchQuery, Movie> {
    return SearchDataSource(cor, remoteDataSource, query)
  }
}