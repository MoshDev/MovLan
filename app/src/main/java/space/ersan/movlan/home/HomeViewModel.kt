package space.ersan.movlan.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import space.ersan.movlan.data.source.MoviesRepository
import java.util.*

class HomeViewModel(application: Application, moviesRepository: MoviesRepository) : AndroidViewModel(application) {

  val createTime = Date(System.currentTimeMillis()).toLocaleString()
}