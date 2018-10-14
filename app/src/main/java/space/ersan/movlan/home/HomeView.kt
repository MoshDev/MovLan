package space.ersan.movlan.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_main.view.*
import space.ersan.movlan.R
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.home.list.MoviesListAdapter
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class HomeView(context: Context, private val thumbnailLoader: ImageLoader.Thumbnail) : FrameLayout(
    context) {
  init {
    View.inflate(context, R.layout.view_main, this)
  }

  fun setMovies(result: MovieList) {
    println("setting movies list $result")
    val adapter = MoviesListAdapter(thumbnailLoader) {}
    adapter.items.addAll(result.results!!)

    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(context)
    adapter.notifyDataSetChanged()
  }

  fun showError(error: Exception) {
    error.printStackTrace()
  }

}
