package space.ersan.movlan.movie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import javax.inject.Inject

class DefaultMovieListingViewModel @Inject constructor(
  private val moviesRepository: MoviesRepository,
  private val cor: AppCoroutineDispatchers
) : MovieListingViewModel() {

  private val networkStatus = LiveNetworkStatus()
  private val parentJob = Job()
  private val scope = CoroutineScope(cor.UI + parentJob)

  init {
    refreshMovies()
  }

  override fun getNetworkStatus(): LiveNetworkStatus = networkStatus

  override fun getMovies(clb: (LiveData<PagedList<Movie>>) -> Unit) {
    scope.launch {
      val data = moviesRepository.getPopularMoviesPaginated(
        networkStatus
      )
      clb(data)
    }
  }

  override fun refreshMovies() {
    scope.launch { moviesRepository.invalidate(networkStatus) }
  }

  override fun showMovieDetails(context: Context, movie: Movie) = context.startActivity(
    MovieDetailsActivity.intentFor(context, movie.id)
  )

  override fun onCleared() {
    parentJob.cancel()
    super.onCleared()
  }
}

abstract class MovieListingViewModel : ViewModel() {
  abstract fun getNetworkStatus(): LiveNetworkStatus
  abstract fun getMovies(clb: (LiveData<PagedList<Movie>>) -> Unit)
  abstract fun refreshMovies()
  abstract fun showMovieDetails(context: Context, movie: Movie)
}