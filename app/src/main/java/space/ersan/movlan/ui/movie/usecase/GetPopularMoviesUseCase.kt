package space.ersan.movlan.ui.movie.usecase

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.domain.UseCase
import space.ersan.themoviedbapi.model.movie.Movie
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
  private val repository: MoviesRepository
) : UseCase {

  operator fun invoke(scope: CoroutineScope): LiveData<PagedList<Movie>> {
    val pagedListConfig = PagedList.Config.Builder()
      .setEnablePlaceholders(false)
      .setInitialLoadSizeHint(20)
      .setPageSize(20)
      .build()

    return LivePagedListBuilder(
      PopularMoviesDataSource.Factory(scope, repository),
      pagedListConfig
    ).build()
  }
}

private class PopularMoviesDataSource(
  private val scope: CoroutineScope,
  private val repository: MoviesRepository
) : PageKeyedDataSource<Int, Movie>() {
  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Int, Movie>
  ) {
    scope.launch {
      val movies = repository.getPopularMovies(1)
      callback.onResult(movies.movies!!, null, 2)
    }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    scope.launch {
      val page = params.key
      val movies = repository.getPopularMovies(page)
      callback.onResult(movies.movies!!, page.inc())
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

  class Factory(
    private val scope: CoroutineScope,
    private val repository: MoviesRepository
  ) : DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> {
      return PopularMoviesDataSource(scope, repository)
    }
  }
}
