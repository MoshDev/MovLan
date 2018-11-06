package space.ersan.movlan.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.common.NativeView
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity() {

  companion object {
    private const val EXTRA_MOVIE_ID = "extra_movie_id"

    fun intentFor(context: Context, movieId: Int): Intent =
        Intent(context, MovieDetailsActivity::class.java).apply {
          putExtra(EXTRA_MOVIE_ID, movieId)
        }
  }

  @Inject
  lateinit var nativeView: NativeView

  @Inject
  lateinit var viewMovie: MovieDetailsView

  @Inject
  lateinit var viewModelDefault: MovieDetailsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
    Movlan.injector.inject(this)
    setContentView(nativeView.getView())

    viewModelDefault.getMovieDetails(movieId) {
      it.observe({ lifecycle }, viewMovie::setMovie)
    }
    viewMovie.observeHomePageButtonClicks {
      it?.also { homepage -> viewModelDefault.openHomePage(this@MovieDetailsActivity, homepage) }
    }
  }
}