package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.model.AppConfig

@Module
class AppConfigModule(private val appConfig: AppConfig) {

  @AppScope
  @Provides
  fun appConfig() = appConfig
}