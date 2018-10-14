package space.ersan.movlan.image

import android.app.Application
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import space.ersan.movlan.R
import space.ersan.movlan.data.model.Movie

sealed class ImageLoader(private val requestManager: RequestManager,
                         private val imagesBaseUrl: String,
                         private val pathResolver: (Movie) -> String?) {

  private companion object {
    private val defaultRequestOptions = RequestOptions()
        .fallback(R.drawable.ic_image_fallback)
        .error(R.drawable.ic_image_error)
  }

  class Thumbnail(application: Application) : ImageLoader(Glide.with(application)
      .applyDefaultRequestOptions(defaultRequestOptions),
      application.getString(R.string.movie_db_thumbnail_url), { it.posterPath })

  class Poster(application: Application) : ImageLoader(Glide.with(application)
      .applyDefaultRequestOptions(defaultRequestOptions),
      application.getString(R.string.movie_db_thumbnail_url), { it.posterPath })

  fun loadImage(imageView: ImageView, movie: Movie) {
    requestManager.load("$imagesBaseUrl${pathResolver(movie)}")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
  }
}