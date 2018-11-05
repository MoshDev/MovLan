package space.ersan.movlan.search.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.home.builder.HomeComponent
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.search.*
import javax.inject.Scope

@Component(dependencies = [HomeComponent::class], modules = [MovieSearchModule::class])
@MovieSearchScope
interface MovieSearchComponent {
  fun inject(fragment: MovieSearchFragment)
}

@Module
class MovieSearchModule(private val fragment: MovieSearchFragment) {

  @Provides
  @MovieSearchScope
  fun provideView(posterLoader: ImageLoader.Poster): MovieSearchView {
    return DefaultMovieSearchView(fragment.requireContext(), posterLoader)
  }

  @Provides
  @MovieSearchScope
  fun provideNativeView(view: MovieSearchView) = view as NativeView

  @Provides
  @MovieSearchScope
  fun provideViewModel(viewModelProvider: ViewModelProvider): MovieSearchViewModel = viewModelProvider.get(
      DefaultMovieSearchViewModel::class.java)

}

@Scope
annotation class MovieSearchScope