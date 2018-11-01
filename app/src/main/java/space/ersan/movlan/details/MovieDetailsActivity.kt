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
  lateinit var presenter: MovieDetailsPresenter
  @Inject
  lateinit var view: NativeView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
    Movlan.injector.inject(this, movieId)
    setContentView(view.getView())
    presenter.onCreate()
  }
}