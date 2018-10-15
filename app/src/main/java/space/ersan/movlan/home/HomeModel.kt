package space.ersan.movlan.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie

class HomeModel(private val lifecycleOwner: LifecycleOwner, private val viewModel: HomeViewModel) {

  fun observeLoadingStatus(clb: (Boolean) -> Unit) {
    viewModel.isLoading.observe(lifecycleOwner, Observer(clb))
  }

  fun observeMovies(clb: (PagedList<Movie>) -> Unit) {
    viewModel.movies.observe(lifecycleOwner, Observer(clb))
  }

  fun viewModel() = viewModel

}
