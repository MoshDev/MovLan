package space.ersan.movlan.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import space.ersan.movlan.utils.AppCoroutineDispatchers
import space.ersan.movlan.utils.LiveNetworkStatus
import space.ersan.themoviedbapi.model.movie.Movies
import javax.inject.Inject

class FeedViewModel @Inject constructor(
  cor: AppCoroutineDispatchers,
  private val interactor: FeedInteractor
) : ViewModel() {

  val feedData = MutableLiveData<FeedMovies>()
  val networkStatus = LiveNetworkStatus()

  private val job = SupervisorJob()
  private val scope = CoroutineScope(cor.UI + job)

  init {
    loadFeedMovies()
  }

  private fun loadFeedMovies() {
    scope.launch {
      networkStatus.postLoading()
      try {
        val nowPlaying = interactor.getNowPlayingMovies()
        val popularMovies = interactor.getPopularMovies()
        val upcomingMovies = interactor.getUpcomingMovies()

        val feed = FeedMovies(nowPlaying, popularMovies, upcomingMovies)

        feedData.postValue(feed)
        networkStatus.postLoaded()
      } catch (err: Exception) {
        networkStatus.postError { loadFeedMovies() }
      }
    }
  }

  override fun onCleared() {
    job.cancel()
    super.onCleared()
  }
}

class FeedMovies(val nowPlaying: Movies, val popular: Movies, val upcoming: Movies)