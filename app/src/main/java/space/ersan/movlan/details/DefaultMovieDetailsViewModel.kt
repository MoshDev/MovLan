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

class DefaultMovieDetailsViewModel(private val moviesRepository: MoviesRepository,
                                   cor: AppCoroutineDispatchers)
  : ViewModel(), MovieDetailsViewModel {

  private val parentJob = Job()
  private val scope = CoroutineScope(cor.UI + parentJob)

  override fun getMovieDetails(movieId: Int, clb: (LiveData<Movie>) -> Unit) {
    val liveData = MutableLiveData<Movie>()
    clb(liveData)
    scope.launch {
      moviesRepository.getMovieDetails(movieId) {
        liveData.postValue(it)
      }
    }
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
  fun getMovieDetails(movieId: Int, clb: (LiveData<Movie>) -> Unit)
  fun openHomePage(context: Context, homePage: String?)
}
