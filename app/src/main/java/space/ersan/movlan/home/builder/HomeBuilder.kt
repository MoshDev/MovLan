package space.ersan.movlan.home.builder

import androidx.lifecycle.ViewModelProviders
import space.ersan.movlan.app.builder.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.data.source.MoviesRepository
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
  fun provideHomePresenter(homeView: HomeView, homeModel: HomeModel): HomePresenter {
    return HomePresenter(homeView, homeModel)
  }

  @Provides
  @HomeScope
  fun provideHomeView(thumbnailLoader: ImageLoader.Thumbnail): HomeView {
    return HomeView(homeActivity, thumbnailLoader)
  }

  @Provides
  @HomeScope
  fun provideHomeModel(viewModel: HomeViewModel) = HomeModel(viewModel)

  @Provides
  @HomeScope
  fun provideHomeViewModel(factory: MovlanViewModelFactory) = ViewModelProviders.of(homeActivity,
      factory).get(HomeViewModel::class.java)

}

@Scope
annotation class HomeScope