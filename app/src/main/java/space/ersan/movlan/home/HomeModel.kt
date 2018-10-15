package space.ersan.movlan.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.utils.NetworkStatus

class HomeModel(private val lifecycleOwner: LifecycleOwner, private val viewModel: HomeViewModel) {

  fun observeNetworkStatus(clb: (NetworkStatus) -> Unit) {
    viewModel.networkStatus.observe(lifecycleOwner, Observer(clb))
  }

  fun observeMovies(clb: (PagedList<Movie>) -> Unit) {
    viewModel.movies.observe(lifecycleOwner, Observer(clb))
  }

  fun viewModel() = viewModel

}
