package space.ersan.movlan.search

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.details.MovieDetailsActivity
import java.util.concurrent.atomic.AtomicReference

class MovieSearchViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

  private val lastQuery = AtomicReference<String?>(null)

  fun isTheSameQuery(query: String): Boolean{
    return query == lastQuery.get()
  }

  fun searchFor(query: String): LiveData<PagedList<Movie>> {
    return moviesRepository.searchMovies(query)
  }

  fun showMovieDetails(context: Context, movie: Movie) {
    context.startActivity(MovieDetailsActivity.intentFor(context, movie.id))
  }
}