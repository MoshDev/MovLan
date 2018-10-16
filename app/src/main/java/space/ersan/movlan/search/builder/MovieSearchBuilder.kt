package space.ersan.movlan.search.builder

import androidx.lifecycle.ViewModelProviders
import space.ersan.movlan.app.builder.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.search.MovieSearchView
import space.ersan.movlan.search.MovieSearchViewModel
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.search.MovieSearchActivity
import space.ersan.movlan.search.MovieSearchPresenter
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [MovieSearchModule::class])
@MovieSearchScope
interface MovieSearchComponent {
  fun inject(activity: MovieSearchActivity)
}

@Module
class MovieSearchModule(private val activity: MovieSearchActivity) {

  @Provides
  @MovieSearchScope
  fun providePresenter(view: MovieSearchView, model: MovieSearchViewModel): MovieSearchPresenter {
    return MovieSearchPresenter(activity, view, model)
  }

  @Provides
  @MovieSearchScope
  fun provideView(posterLoader: ImageLoader.Poster): MovieSearchView {
    return MovieSearchView(activity, posterLoader)
  }

  @Provides
  @MovieSearchScope
  fun provideViewModel(factory: MovlanViewModelFactory) = ViewModelProviders.of(activity,
      factory).get(MovieSearchViewModel::class.java)

}

@Scope
annotation class MovieSearchScope