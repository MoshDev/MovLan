package space.ersan.movlan.search

import android.annotation.SuppressLint
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.widget.SearchView
import space.ersan.movlan.home.HomeView
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class MovieSearchView(context: Context, posterLoader: ImageLoader.Poster) : HomeView(
    context,
    posterLoader) {

  public override fun setTitle(title: String) {
    super.setTitle(title)
  }
}