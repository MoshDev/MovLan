package space.ersan.movlan.home

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.movlan.utils.NetworkStatus

class HomeViewModel(application: Application,
                    private val moviesRepository: MoviesRepository,
                    private val networkStatus: LiveNetworkStatus)
  : AndroidViewModel(application) {

  private val movies = moviesRepository.getPopularMovies()

  init {
    refreshData()
  }

  fun refreshData() {
    moviesRepository.invalidate()
  }

  fun observeNetworkStatus(lifecycleOwner: LifecycleOwner, clb: (NetworkStatus) -> Unit) {
    networkStatus.observe(lifecycleOwner, Observer(clb))
  }

  fun observeMovies(lifecycleOwner: LifecycleOwner, clb: (PagedList<Movie>) -> Unit) {
    movies.observe(lifecycleOwner, Observer(clb))
  }

  fun showMovieDetails(id: Int) {
    val app: MovlanApp = getApplication()
    val intent = MovieDetailsActivity.intentFor(app, id)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    app.startActivity(intent)
  }

}