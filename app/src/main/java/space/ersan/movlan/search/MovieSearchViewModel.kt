package space.ersan.movlan.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import space.ersan.movlan.data.source.MoviesRepository

class MovieSearchViewModel(application: Application, private val moviesRepository: MoviesRepository) : AndroidViewModel(
    application) {

}