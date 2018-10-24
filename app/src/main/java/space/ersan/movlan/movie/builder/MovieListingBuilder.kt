package space.ersan.movlan.movie.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.home.builder.HomeComponent
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.movie.MovieListingFragment
import space.ersan.movlan.movie.MovieListingPresenter
import space.ersan.movlan.movie.MovieListingView
import space.ersan.movlan.movie.MovieListingViewModel
import javax.inject.Scope

@Component(dependencies = [HomeComponent::class], modules = [MovieListingModule::class])
@MovieListingScope
interface MovieListingComponent {
  fun inject(fragment: MovieListingFragment)
}

@Module
class MovieListingModule(private val fragment: MovieListingFragment) {

  @Provides
  @MovieListingScope
  fun provideHomePresenter(homeView: MovieListingView, viewModel: MovieListingViewModel): MovieListingPresenter {
    return MovieListingPresenter(fragment, homeView, viewModel)
  }

  @Provides
  @MovieListingScope
  fun provideHomeView(posterLoader: ImageLoader.Poster): MovieListingView {
    return MovieListingView(fragment.requireContext(), posterLoader)
  }

  @Provides
  @MovieListingScope
  fun provideHomeViewModel(viewModelProvider: ViewModelProvider) = viewModelProvider.get(MovieListingViewModel::class.java)

}

@Scope
annotation class MovieListingScope