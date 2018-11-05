package space.ersan.movlan.movie

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.utils.LiveNetworkStatus

class DefaultMovieListingViewModel(application: Application,
                                   private val moviesRepository: MoviesRepository,
                                   private val networkStatus: LiveNetworkStatus)
  : AndroidViewModel(application), MovieListingViewModel {

  init {
    refreshMovies()
  }

  override fun getNetworkStatus(): LiveNetworkStatus = networkStatus
  override fun getMovies(): LiveData<PagedList<Movie>> = moviesRepository.getPopularMoviesPaginated()
  override fun refreshMovies() = moviesRepository.invalidate()
  override fun showMovieDetails(context: Context, movie: Movie) = context.startActivity(
      MovieDetailsActivity.intentFor(context, movie.id))

}

interface MovieListingViewModel {
  fun getNetworkStatus(): LiveNetworkStatus
  fun getMovies(): LiveData<PagedList<Movie>>
  fun refreshMovies()
  fun showMovieDetails(context: Context, movie: Movie)
}