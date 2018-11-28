package space.ersan.movlan.ui.feed.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.ui.feed.FeedFragment
import space.ersan.movlan.ui.feed.FeedViewModel
import space.ersan.movlan.ui.feed.view.FeedView
import space.ersan.movlan.ui.home.builder.HomeComponent
import javax.inject.Scope

@Scope
annotation class FeedScope

@FeedScope
@Component(dependencies = [HomeComponent::class], modules = [FeedModule::class])
interface FeedComponent {
  fun inject(feedFragment: FeedFragment)
}

@Module
class FeedModule(private val feedFragment: FeedFragment) {

  @FeedScope
  @Provides
  fun view(): FeedView =
    FeedView(feedFragment.requireActivity())

  @FeedScope
  @Provides
  fun nativeView(feedView: FeedView): NativeView = feedView

  @FeedScope
  @Provides
  fun provideVM(viewModelProvider: ViewModelProvider): FeedViewModel =
    viewModelProvider.get(FeedViewModel::class.java)
}
