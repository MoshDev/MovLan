package space.ersan.movlan.home.movie.builder

import androidx.lifecycle.ViewModelProviders
import space.ersan.movlan.app.builder.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.home.movie.MovieListingFragment
import space.ersan.movlan.home.movie.MovieListingPresenter
import space.ersan.movlan.home.movie.MovieListingView
import space.ersan.movlan.home.movie.MovieListingViewModel
import space.ersan.movlan.image.ImageLoader
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [MovieListingModule::class])
@HomeScope
interface MovieListingComponent {
  fun inject(fragment: MovieListingFragment)
}

@Module
class MovieListingModule(private val fragment: MovieListingFragment) {

  @Provides
  @HomeScope
  fun provideHomePresenter(homeView: MovieListingView, viewModel: MovieListingViewModel): MovieListingPresenter {
    return MovieListingPresenter(fragment, homeView, viewModel)
  }

  @Provides
  @HomeScope
  fun provideHomeView(posterLoader: ImageLoader.Poster): MovieListingView {
    return MovieListingView(fragment.requireContext(), posterLoader)
  }

  @Provides
  @HomeScope
  fun provideHomeViewModel(factory: MovlanViewModelFactory) = ViewModelProviders.of(fragment,
      factory).get(MovieListingViewModel::class.java)

}

@Scope
annotation class HomeScope