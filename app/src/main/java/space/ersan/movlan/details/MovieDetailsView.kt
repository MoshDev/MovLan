package space.ersan.movlan.details

import android.annotation.SuppressLint
import android.content.Context
import space.ersan.movlan.common.mvp.BaseView
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class MovieDetailsView(context: Context, backdropLoader: ImageLoader.Backdrop) : BaseView(context) {

  init {

  }

  fun setMovie(movie: Movie?) {

  }
}