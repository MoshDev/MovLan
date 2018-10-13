package space.ersan.movlan.image

import android.app.Application
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import space.ersan.movlan.R

sealed class ImageLoader(private val requestManager: RequestManager, private val imagesBaseUrl: String) {

  private companion object {
    private val defaultRequestOptions = RequestOptions()
        .fallback(R.drawable.ic_image_fallback)
        .error(R.drawable.ic_image_error)
  }

  class Thumbnail(application: Application) : ImageLoader(Glide.with(application)
      .applyDefaultRequestOptions(defaultRequestOptions), application.getString(R.string.movie_db_thumbnail_url))

  class Poster(application: Application) : ImageLoader(Glide.with(application)
      .applyDefaultRequestOptions(defaultRequestOptions), application.getString(R.string.movie_db_thumbnail_url))

  fun loadImage(imageView: ImageView, url: String?) {
    requestManager.load("$imagesBaseUrl$url").transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
  }
}