package space.ersan.movlan.app

import android.app.Application
import space.ersan.movlan.R
import space.ersan.movlan.app.builder.Movlan
import space.ersan.movlan.app.builder.Injector
import space.ersan.movlan.data.model.AppConfig

class MovlanApp : Application() {

  override fun onCreate() {
    super.onCreate()
    val appConfig = AppConfig(
        movieDbApiKey = getString(R.string.movie_db_api_key),
        movieDbApiBaseUrl = getString(R.string.movie_db_api_base_url)
    )
    Movlan.injector = Injector(this, appConfig)
  }
}