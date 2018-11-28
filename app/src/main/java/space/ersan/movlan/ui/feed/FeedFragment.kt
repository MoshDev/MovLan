package space.ersan.movlan.ui.feed

import android.os.Bundle
import space.ersan.movlan.app.ComponentProvider
import space.ersan.movlan.app.builder.Injector
import space.ersan.movlan.common.BaseFragment
import space.ersan.movlan.ui.feed.view.FeedView
import space.ersan.movlan.ui.home.builder.HomeComponent

class FeedFragment : BaseFragment<FeedView, FeedViewModel>() {

  override fun onInject(
    savedInstanceState: Bundle?,
    injector: Injector
  ) {
    injector.inject(
      this,
      (requireActivity() as ComponentProvider<HomeComponent>).getComponent()
    )
  }

  override fun startBinding() {
    viewModel.networkStatus.observe({ lifecycle }, { view.setNetworkStatus(it) })
    viewModel.feedData.observe({ lifecycle }, { view.setFeedMovies(it) })
  }
}