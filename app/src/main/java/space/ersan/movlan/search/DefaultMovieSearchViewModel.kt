package space.ersan.movlan.search

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity

class DefaultMovieSearchViewModel(application: Application,
                                  private val moviesRepository: MoviesRepository)
  : AndroidViewModel(application), MovieSearchViewModel {

  private val searchLiveData: MediatorLiveData<PagedList<Movie>> = MediatorLiveData()
  private var lastSearchLiveData: LiveData<PagedList<Movie>>? = null
  private var searchQuery: String? = null

  override fun getSearchQuery(): String? = searchQuery

  override fun searchMovies(query: String): LiveData<PagedList<Movie>> {
    searchQuery = query
    lastSearchLiveData?.let {
      searchLiveData.removeSource(it)
    }
    lastSearchLiveData = moviesRepository.searchMovies(query)
    searchLiveData.addSource(lastSearchLiveData!!) { searchLiveData.postValue(it) }
    return lastSearchLiveData!!
  }

  override fun showMovieDetails(context: Context, movie: Movie) = context.startActivity(
      MovieDetailsActivity.intentFor(context, movie.id))
}

interface MovieSearchViewModel {

  fun getSearchQuery(): String?
  fun searchMovies(query: String): LiveData<PagedList<Movie>>
  fun showMovieDetails(context: Context, movie: Movie)

}
