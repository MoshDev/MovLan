package space.ersan.movlan.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.AppCoroutineDispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultMovieDetailsViewModel @Inject constructor(
  cor: AppCoroutineDispatchers,
  private val moviesRepository: MoviesRepository
) : MovieDetailsViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = cor.UI + SupervisorJob()
  private val movieDetailsLiveData = MutableLiveData<Movie>()

  override fun getMovieDetails(movieId: Int): LiveData<Movie> {
    if (movieDetailsLiveData.value == null) {
      launch {
        moviesRepository.getMovieDetails(movieId) {
          movieDetailsLiveData.postValue(it)
        }
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
    coroutineContext.cancel()
    super.onCleared()
  }
}

abstract class MovieDetailsViewModel : ViewModel() {
  abstract fun getMovieDetails(movieId: Int): LiveData<Movie>
  abstract fun openHomePage(context: Context, homePage: String?)
}
