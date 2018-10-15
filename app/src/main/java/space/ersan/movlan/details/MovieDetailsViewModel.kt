package space.ersan.movlan.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {

  var movieId: Int? = null

}