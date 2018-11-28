package space.ersan.movlan.ui.movie.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.ui.home.builder.HomeComponent
import space.ersan.movlan.ui.movie.DefaultMovieListingViewModel
import space.ersan.movlan.ui.movie.DefaultMoviesListingView
import space.ersan.movlan.ui.movie.MovieListingFragment
import space.ersan.movlan.ui.movie.MovieListingViewModel
import space.ersan.movlan.ui.movie.MoviesListingView
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
  fun provideHomeView(posterLoader: ImageLoader.Poster): MoviesListingView {
    return DefaultMoviesListingView(fragment.requireContext(), posterLoader)
  }

  @Provides
  @MovieListingScope
  fun provideHomeViewModel(viewModelProvider: ViewModelProvider): MovieListingViewModel =
    viewModelProvider.get(
      DefaultMovieListingViewModel::class.java
    )

  @Provides
  @MovieListingScope
  fun provideNativeView(viewMovies: MoviesListingView) = viewMovies as NativeView
}

@Scope
annotation class MovieListingScope