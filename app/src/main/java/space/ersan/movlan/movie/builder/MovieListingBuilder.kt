package space.ersan.movlan.movie.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.home.builder.HomeComponent
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.movie.*
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
  fun provideHomePresenter(homeViewMovies: MoviesListingView, viewModel: MovieListingViewModel): MovieListingPresenter {
    return MovieListingPresenter(fragment, homeViewMovies, viewModel)
  }

  @Provides
  @MovieListingScope
  fun provideHomeView(posterLoader: ImageLoader.Poster): MoviesListingView {
    return DefaultMoviesListingView(fragment.requireContext(), posterLoader)
  }

  @Provides
  @MovieListingScope
  fun provideHomeViewModel(viewModelProvider: ViewModelProvider) = viewModelProvider.get(
      MovieListingViewModel::class.java)

  @Provides
  @MovieListingScope
  fun provideNativeView(viewMovies: MoviesListingView) = viewMovies as NativeView

}

@Scope
annotation class MovieListingScope