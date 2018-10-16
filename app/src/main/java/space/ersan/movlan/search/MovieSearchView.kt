package space.ersan.movlan.search

import android.annotation.SuppressLint
import android.content.Context
import space.ersan.movlan.common.mvp.BaseView
import space.ersan.movlan.image.ImageLoader

@SuppressLint("ViewConstructor")
class MovieSearchView(context: Context, private val posterLoader: ImageLoader.Poster) : BaseView(
    context) {

}