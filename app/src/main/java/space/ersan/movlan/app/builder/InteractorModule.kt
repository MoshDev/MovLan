package space.ersan.movlan.app.builder

import dagger.Binds
import dagger.Module
import space.ersan.movlan.common.BaseInteractor
import space.ersan.movlan.ui.feed.FeedInteractor

@Module
abstract class InteractorModule {

  @Binds
  abstract fun provideFeedInteractor(instance: FeedInteractor): BaseInteractor
}