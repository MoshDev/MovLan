package space.ersan.movlan.movie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.movie.usecase.GetPopularMoviesUsecase
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.themoviedbapi.model.movie.Movie
import javax.inject.Inject

class DefaultMovieListingViewModel @Inject constructor(
  private val cor: AppCoroutineDispatchers,
  private val popularMoviesUsecase: GetPopularMoviesUsecase
) : MovieListingViewModel() {

  private val networkStatus = LiveNetworkStatus()
  private val parentJob = SupervisorJob()
  private val scope = CoroutineScope(cor.UI + parentJob)

  init {
    refreshMovies()
  }

  override fun getNetworkStatus(): LiveNetworkStatus = networkStatus

  override fun getMovies(): LiveData<PagedList<Movie>> {
    return popularMoviesUsecase(scope)
  }

  override fun refreshMovies() {
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
  abstract fun getMovies(): LiveData<PagedList<Movie>>
  abstract fun refreshMovies()
  abstract fun showMovieDetails(context: Context, movie: Movie)
}