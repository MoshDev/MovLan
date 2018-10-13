package space.ersan.movlan.app.builder

import android.app.Application
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanApp

@Module
class AppModule(private val movlanApp: MovlanApp) {

  @AppScope
  @Provides
  fun movelanApp(): MovlanApp = movlanApp

  @AppScope
  @Provides
  fun app(): Application = movlanApp
}