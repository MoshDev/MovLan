package space.ersan.movlan.image

import android.app.Application
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import space.ersan.movlan.BuildConfig
import space.ersan.movlan.R
import space.ersan.themoviedbapi.model.movie.Movie

sealed class ImageLoader(
  private val requestManager: RequestManager,
  private val imagesBaseUrl: String,
  private val pathResolver: (Movie) -> String?
) {

  private companion object {
    private val defaultRequestOptions = RequestOptions()
      .fallback(R.drawable.bg_image_error)
      .error(R.drawable.bg_image_error)
  }

  class Poster(application: Application) : ImageLoader(Glide.with(application)
    .applyDefaultRequestOptions(defaultRequestOptions),
    application.getString(R.string.movie_db_poster_url), { it.posterPath })

  class Backdrop(application: Application) : ImageLoader(Glide.with(application)
    .applyDefaultRequestOptions(defaultRequestOptions),
    application.getString(R.string.movie_db_backdrop_url), { it.backdropPath })

  fun loadImage(imageView: ImageView, movie: Movie) {
    val path = pathResolver(movie)?.let { "$imagesBaseUrl$it" }
    if (BuildConfig.DEBUG) println(path)
    requestManager.load(path)
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(imageView)
  }
}