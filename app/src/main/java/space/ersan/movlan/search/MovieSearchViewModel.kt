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

class MovieSearchViewModel(application: Application,
                           private val moviesRepository: MoviesRepository)
  : AndroidViewModel(application) {

  private val searchLiveData: MediatorLiveData<PagedList<Movie>> = MediatorLiveData()

  fun getSearchData(): LiveData<PagedList<Movie>> = searchLiveData

  var searchTextQuery: String? = null
    private set

  private var lastSearchLiveData: LiveData<PagedList<Movie>>? = null

  fun searchMovies(query: String) {
    searchTextQuery = query
    lastSearchLiveData?.let {
      searchLiveData.removeSource(it)
    }
    lastSearchLiveData = moviesRepository.searchMovies(query)
    searchLiveData.addSource(lastSearchLiveData!!) { searchLiveData.postValue(it) }
  }

  fun showMovieDetails(context: Context, movie: Movie) {
    context.startActivity(MovieDetailsActivity.intentFor(context, movie.id))
  }
}