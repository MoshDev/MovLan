package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.utils.AppCoroutineDispatchers

@Module
class AppCoroutineModule {

  @AppScope
  @Provides
  fun appCoroutineDispatchers() = AppCoroutineDispatchers()
}