package space.ersan.movlan.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import space.ersan.movlan.R

//private val defaultRequestOptions = RequestOptions().fallback(R.drawable.bg_default_image_fallback)
//  .error(R.drawable.bg_default_image_fallback)
//
//fun ImageView.load(url: String?) {
//  if (url == null) {
//    return
//  }
//
//  Glide.with(context)
//    .applyDefaultRequestOptions(defaultRequestOptions)
//    .load(url)
//    .transition(DrawableTransitionOptions.withCrossFade())
//    .into(this)
//}