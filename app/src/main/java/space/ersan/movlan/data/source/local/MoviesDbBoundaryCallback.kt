package space.ersan.movlan.data.source.local

import androidx.paging.PagedList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.remote.RemoteDataSource
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.Maybe
import space.ersan.movlan.utils.NetworkStatus

class MoviesDbBoundaryCallback(private val cor: AppCoroutineDispatchers,
                               private val remoteDataSource: RemoteDataSource,
                               private val localDataSource: LocalDataSource,
                               private val networkStatus: LiveNetworkStatus)
  : PagedList.BoundaryCallback<Movie>() {

  override fun onZeroItemsLoaded() {
    val page = 1
    GlobalScope.launch(cor.NETWORK) {
      val genres = remoteDataSource.getGenres()
      when (genres) {
        is Maybe.Some -> withContext(cor.IO) { localDataSource.insertAllGenres(genres.value.genres!!) }
        is Maybe.Error -> {
          networkStatus.postValue(NetworkStatus.Error(::onZeroItemsLoaded))
          return@launch
        }
      }
      val movies = remoteDataSource.getPopularMovies(page)
      when (movies) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.deleteAllMovies()
          localDataSource.insertAll(page, movies.value.results ?: emptyList())
          networkStatus.postValue(NetworkStatus.Loaded)
        }
        is Maybe.Error -> {
          networkStatus.postValue(NetworkStatus.Error())
        }
      }
    }
  }

  override fun onItemAtEndLoaded(itemAtEnd: Movie) {
    networkStatus.postValue(NetworkStatus.Loading)

    val nextPage = itemAtEnd.page.inc()
    GlobalScope.launch(cor.NETWORK) {
      val result = remoteDataSource.getPopularMovies(nextPage)
      when (result) {
        is Maybe.Some -> withContext(cor.IO) {
          localDataSource.insertAll(nextPage,
              result.value.results ?: emptyList())
          networkStatus.postValue(NetworkStatus.Loaded)
        }
        is Maybe.Error -> networkStatus.postValue(NetworkStatus.Error {
          onItemAtEndLoaded(itemAtEnd)
        })
      }
    }
  }

}