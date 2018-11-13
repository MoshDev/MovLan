package space.ersan.movlan.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.AppCoroutineDispatchers
import javax.inject.Inject

class DefaultMovieDetailsViewModel @Inject constructor(private val moviesRepository: MoviesRepository,
                                                       cor: AppCoroutineDispatchers)
  : ViewModel(), MovieDetailsViewModel {

  private val parentJob: Job = Job()
  private val scope = CoroutineScope(cor.UI + parentJob)
  private val movieDetailsLiveData = MutableLiveData<Movie>()
  private var isParentJobRunning = false

  override fun getMovieDetails(movieId: Int): LiveData<Movie> {
    if (movieDetailsLiveData.value == null && !isParentJobRunning) {
      isParentJobRunning = true
      scope.launch {
        moviesRepository.getMovieDetails(movieId) {
          movieDetailsLiveData.postValue(it)
        }
        isParentJobRunning = false
      }
    }
    return movieDetailsLiveData
  }

  override fun openHomePage(context: Context, homePage: String?) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(homePage))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
  }

  override fun onCleared() {
    parentJob.cancel()
    super.onCleared()
  }
}

interface MovieDetailsViewModel {
  fun getMovieDetails(movieId: Int): LiveData<Movie>
  fun openHomePage(context: Context, homePage: String?)
}
