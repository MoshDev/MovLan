package space.ersan.movlan.data.source.remote.search

import androidx.paging.DataSource
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository

class SearchDataSourceFactory(
  private val repository: MoviesRepository,
  private val query: String
) : DataSource.Factory<SearchQuery, Movie>() {

  override fun create(): DataSource<SearchQuery, Movie> {
    return MoviesSearchDataSource(repository, query)
  }
}