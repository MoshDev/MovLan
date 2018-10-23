package space.ersan.movlan.search.builder

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import space.ersan.movlan.app.builder.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.home.builder.HomeComponent
import space.ersan.movlan.home.builder.HomeModule
import space.ersan.movlan.search.MovieSearchView
import space.ersan.movlan.search.MovieSearchViewModel
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.search.MovieSearchFragment
import space.ersan.movlan.search.MovieSearchPresenter
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
  fun providePresenter(view: MovieSearchView, model: MovieSearchViewModel): MovieSearchPresenter {
    return MovieSearchPresenter(fragment, view, model)
  }

  @Provides
  @MovieSearchScope
  fun provideView(posterLoader: ImageLoader.Poster): MovieSearchView {
    return MovieSearchView(fragment.requireContext(), posterLoader)
  }

  @Provides
  @MovieSearchScope
  fun provideViewModel(viewModelProvider: ViewModelProvider) = viewModelProvider.get(MovieSearchViewModel::class.java)

}

@Scope
annotation class MovieSearchScope