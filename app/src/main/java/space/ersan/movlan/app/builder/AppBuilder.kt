package space.ersan.movlan.app.builder

import com.google.gson.Gson
import dagger.Component
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.viewmodel.MovlanViewModelFactory
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(
  modules = [
    AppModule::class, AppConfigModule::class, GsonModule::class,
    ImageLoaderModule::class, RetrofitModule::class, MoviesDataSourceModule::class,
    AppCoroutineModule::class, DatabaseModule::class, ViewModelModule::class, UseCasesModule::class, InteractorModule::class
  ]
)
interface AppComponent {

  fun exposeMoviesRepository(): MoviesRepository

  fun exposePosterImageLoader(): ImageLoader.Poster

  fun exposeBackdropImageLoader(): ImageLoader.Backdrop

  fun exposeViewModelFactory(): MovlanViewModelFactory

  fun exposeGson(): Gson
}