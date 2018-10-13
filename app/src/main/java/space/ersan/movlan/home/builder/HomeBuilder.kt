package space.ersan.movlan.home.builder

import space.ersan.movlan.app.builder.AppComponent
import space.ersan.movlan.home.HomeActivity
import space.ersan.movlan.home.HomeModel
import space.ersan.movlan.home.HomePresenter
import space.ersan.movlan.home.HomeView
import dagger.Component
import dagger.Module
import dagger.Provides
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
  fun provideHomeView(): HomeView {
    return HomeView(homeActivity)
  }

  @Provides
  @HomeScope
  fun provideHomeModel() = HomeModel(homeActivity)

}

@Scope
annotation class HomeScope