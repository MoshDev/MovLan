package space.ersan.movlan.app.builder

import android.app.Application
import dagger.Component
import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.AppConfig
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(modules = [AppModule::class, AppConfigModule::class, GsonModule::class, ImageLoaderModule::class, RetrofitModule::class, MoviesDataSourceModule::class, AppCoroutineModule::class, DatabaseModule::class])
interface AppComponent {

  fun exposeAppConfig(): AppConfig

  fun exposeMovlanApp(): MovlanApp

  fun exposeApplication(): Application


}
