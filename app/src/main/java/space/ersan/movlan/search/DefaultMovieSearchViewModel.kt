package space.ersan.movlan.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.utils.AppCoroutineDispatchers
import javax.inject.Inject

class DefaultMovieSearchViewModel @Inject constructor(
  private val moviesRepository: MoviesRepository,
  cor: AppCoroutineDispatchers
) : ViewModel(), MovieSearchViewModel {

  private val parentJob = Job()
  private val scope = CoroutineScope(cor.UI + parentJob)

  private val searchLiveData: MediatorLiveData<PagedList<Movie>> = MediatorLiveData()
  private var lastSearchLiveData: LiveData<PagedList<Movie>>? = null
  private var searchQuery: String? = null

  override fun getSearchQuery(): String? = searchQuery

  override fun searchMovies(query: String, clb: (LiveData<PagedList<Movie>>) -> Unit) {
    scope.launch {
      searchQuery = query
      lastSearchLiveData?.let {
        searchLiveData.removeSource(it)
      }
      lastSearchLiveData = moviesRepository.searchMovies(query)
      searchLiveData.addSource(lastSearchLiveData!!) { searchLiveData.postValue(it) }
      clb(lastSearchLiveData!!)
    }
  }

  override fun showMovieDetails(context: Context, movie: Movie) = context.startActivity(
    MovieDetailsActivity.intentFor(context, movie.id)
  )

  override fun onCleared() {
    parentJob.cancel()
    super.onCleared()
  }
}

interface MovieSearchViewModel {

  fun getSearchQuery(): String?
  fun searchMovies(query: String, clb: (LiveData<PagedList<Movie>>) -> Unit)
  fun showMovieDetails(context: Context, movie: Movie)
}
