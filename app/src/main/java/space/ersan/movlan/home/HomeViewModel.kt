package space.ersan.movlan.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.source.MoviesRepository

class HomeViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

  val isLoading = MutableLiveData<Boolean>().apply { value = false }

  val movies = moviesRepository.getPopularMovies()
  private var genreList: GenreList? = null

  init {
    isLoading.value = true
    moviesRepository.invalidate()
  }

  fun refreshData(){
    moviesRepository.invalidate()
  }
//
//  private fun loadGenres(onNext: () -> Unit) {
//    if (genreList != null) {
//      onNext()
//    } else {
//      moviesRepository.getGenres {
//        when (it) {
//          is Maybe.Some -> {
//            genreList = it.value
//            onNext()
//          }
//          else -> TODO()
//        }
//      }
//    }
//  }
//
//  private fun enrich(movieList: MovieList) {
//    val newList = (movieList.results ?: emptyList()).map { movie ->
//      val ids = movie.genreIds
//      if (ids != null && ids.isNotEmpty()) {
//        val enrichedGenres = ids.map { id -> genreList!!.genres?.first { it?.id == id } }
//        return@map movie.copy(genres = enrichedGenres)
//      }
//      return@map movie
//    }
//
//    movies.postValue(newList)
//  }


}