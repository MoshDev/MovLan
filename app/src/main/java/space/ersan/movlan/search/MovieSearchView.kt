package space.ersan.movlan.search

import android.annotation.SuppressLint
import android.content.Context
import space.ersan.movlan.home.movie.MovieListingView
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class MovieSearchView(context: Context, posterLoader: ImageLoader.Poster) : MovieListingView(
    context,
    posterLoader) {

  public override fun setTitle(title: String) {
    super.setTitle(title)
  }
}