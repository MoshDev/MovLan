package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.AppCoroutineDispatchers

@Module
class ViewModelFactoryModule {

  @AppScope
  @Provides
  fun viewModelFactory(repo: MoviesRepository, cor: AppCoroutineDispatchers) = MovlanViewModelFactory(
      repo,
      cor)

}