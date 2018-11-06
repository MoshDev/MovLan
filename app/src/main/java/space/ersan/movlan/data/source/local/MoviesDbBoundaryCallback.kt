package space.ersan.movlan.data.source.local

import androidx.paging.PagedList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.LiveNetworkStatus

class MoviesDbBoundaryCallback(private val repository: MoviesRepository, private val networkStatus: LiveNetworkStatus)
  : PagedList.BoundaryCallback<Movie>() {

  override fun onZeroItemsLoaded() {
    GlobalScope.launch {
      repository.loadPopularMovies(1, networkStatus)
    }
  }

  override fun onItemAtEndLoaded(itemAtEnd: Movie) {
    GlobalScope.launch {
      val nextPage = itemAtEnd.page.inc()
      repository.loadPopularMovies(nextPage, networkStatus)
    }
  }

}

