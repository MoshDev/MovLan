package space.ersan.movlan.data.source.local

import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository

class MoviesDbBoundaryCallback(private val repository: MoviesRepository)
  : PagedList.BoundaryCallback<Movie>() {

  override fun onZeroItemsLoaded() {
    repository.loadPopularMovies(1)
  }

  override fun onItemAtEndLoaded(itemAtEnd: Movie) {
    val nextPage = itemAtEnd.page.inc()
    repository.loadPopularMovies(nextPage)
  }

}

