package space.ersan.movlan.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.ersan.movlan.app.Movlan
import javax.inject.Inject

class MovieSearchActivity : AppCompatActivity() {

  @Inject
  lateinit var presenter: MovieSearchPresenter
  @Inject
  lateinit var view: MovieSearchView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Movlan.injector.inject(this)
    setContentView(view)
    presenter.onCreate()

    handleSearchIntent(intent)
    println("Mosh 1 MovieSearchActivity ${intent}")
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    handleSearchIntent(intent)
    println("Mosh 2 MovieSearchActivity ${intent}")
  }

  private fun handleSearchIntent(intent: Intent?) {
    if (Intent.ACTION_SEARCH == intent?.action) {
      val query: String? = intent.getStringExtra(SearchManager.QUERY)
      query?.let {
        presenter.searchFor(it)
      }
    }
  }
}

