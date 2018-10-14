package space.ersan.movlan.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.Maybe
import space.ersan.movlan.data.source.MoviesRepository
import java.util.concurrent.atomic.AtomicInteger

class HomeViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

  val isLoading = MutableLiveData<Boolean>().apply { value = false }

  val movies = MutableLiveData<List<Movie>>()
  private val page = AtomicInteger(1)
  private val pendingPage = AtomicInteger(-1)
  private var genreList: GenreList? = null

  init {
    isLoading.value = true
    loadMovies(page.get(), false)
  }

  private fun loadMovies(page: Int, forceUpdate: Boolean) {
    pendingPage.set(page)

    loadGenres {
      moviesRepository.getPopularMovies(page) {
        isLoading.value = false
        pendingPage.set(-1)
        when (it) {
          is Maybe.Some -> enrich(it.value)
        }
      }
    }
  }

  private fun loadGenres(onNext: () -> Unit) {
    if (genreList != null) {
      onNext()
    } else {
      moviesRepository.getGenres {
        when (it) {
          is Maybe.Some -> {
            genreList = it.value
            onNext()
          }
          else -> TODO()
        }
      }
    }
  }

  private fun enrich(movieList: MovieList) {
    val newList = (movieList.results ?: emptyList()).map { movie ->
      val ids = movie.genreIds
      if (ids != null && ids.isNotEmpty()) {
        val enrichedGenres = ids.map { id -> genreList!!.genres?.first { it?.id == id } }
        return@map movie.copy(genres = enrichedGenres)
      }
      return@map movie
    }

    movies.postValue(newList)
  }

  fun loadNextPage() {
    if (pendingPage.get() != -1) {
      return
    }
    loadMovies(page.incrementAndGet(), false)
  }

}