package space.ersan.movlan.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.ersan.movlan.app.Movlan
import javax.inject.Inject

class MovieSearchActivity : AppCompatActivity() {
  companion object {

    fun intentFor(context: Context): Intent = Intent(context, MovieSearchActivity::
    class.java)
  }

  @Inject
  lateinit var presenter: MovieSearchPresenter
  @Inject
  lateinit var view: MovieSearchView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Movlan.injector.inject(this)
    setContentView(view)
    presenter.onCreate()
  }
}

