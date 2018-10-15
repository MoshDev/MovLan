package space.ersan.movlan.home.builder

import androidx.lifecycle.ViewModelProviders
import space.ersan.movlan.app.builder.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.home.*
import space.ersan.movlan.image.ImageLoader
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
@HomeScope
interface HomeComponent {
  fun inject(homeActivity: HomeActivity)
}

@Module
class HomeModule(private val homeActivity: HomeActivity) {

  @Provides
  @HomeScope
  fun provideHomePresenter(homeView: HomeView, viewModel: HomeViewModel): HomePresenter {
    return HomePresenter(homeActivity, homeView, viewModel)
  }

  @Provides
  @HomeScope
  fun provideHomeView(posterLoader: ImageLoader.Poster): HomeView {
    return HomeView(homeActivity, posterLoader)
  }

  @Provides
  @HomeScope
  fun provideHomeViewModel(factory: MovlanViewModelFactory) = ViewModelProviders.of(homeActivity,
      factory).get(HomeViewModel::class.java)

}

@Scope
annotation class HomeScope