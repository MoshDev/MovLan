package space.ersan.movlan.home.builder

import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.home.HomeActivity
import javax.inject.Scope

@Component(modules = [HomeModule::class])
@HomeScope
interface HomeComponent {
  fun inject(homeActivity: HomeActivity)
}

@Module
class HomeModule(private val homeActivity: HomeActivity) {

  //Empty/Ignored

}

@Scope
annotation class HomeScope