package space.ersan.movlan.ui.home.builder

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.builder.AppComponent
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.ui.home.HomeActivity
import space.ersan.movlan.viewmodel.MovlanViewModelFactory
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
@HomeScope
interface HomeComponent {
  fun inject(homeActivity: HomeActivity)
  fun viewModelProvider(): ViewModelProvider
  fun posterImageLoader(): ImageLoader.Poster
}

@Module
class HomeModule(private val homeActivity: HomeActivity) {

  @HomeScope
  @Provides
  fun viewModelProvider(factory: MovlanViewModelFactory) = ViewModelProviders.of(
    homeActivity,
    factory
  )
}

@Scope
annotation class HomeScope