package space.ersan.movlan.app.builder

import android.app.Application
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.LiveNetworkStatus

@Module
class ViewModelFactoryModule {

  @AppScope
  @Provides
  fun viewModelFactory(app: Application, repo: MoviesRepository, networkStatus: LiveNetworkStatus) = MovlanViewModelFactory(
      app,
      repo,
      networkStatus)

}