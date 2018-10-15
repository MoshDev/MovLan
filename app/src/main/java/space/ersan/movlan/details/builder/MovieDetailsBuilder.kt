package space.ersan.movlan.details.builder

import androidx.lifecycle.ViewModelProviders
import space.ersan.movlan.app.builder.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.MovlanViewModelFactory
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.details.MovieDetailsPresenter
import space.ersan.movlan.details.MovieDetailsView
import space.ersan.movlan.details.MovieDetailsViewModel
import space.ersan.movlan.image.ImageLoader
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [MovieDetailsModule::class])
@MovieDetailsScope
interface MovieDetailsComponent {
  fun inject(activity: MovieDetailsActivity)
}

@Module
class MovieDetailsModule(private val activity: MovieDetailsActivity, private val movieId: Int) {

  @Provides
  @MovieDetailsScope
  fun providePresenter(view: MovieDetailsView, model: MovieDetailsViewModel): MovieDetailsPresenter {
    return MovieDetailsPresenter()
  }

  @Provides
  @MovieDetailsScope
  fun provideView(thumbnailLoader: ImageLoader.Thumbnail): MovieDetailsView {
    return MovieDetailsView(activity)
  }

  @Provides
  @MovieDetailsScope
  fun provideViewModel(factory: MovlanViewModelFactory) = ViewModelProviders.of(activity,
      factory).get(MovieDetailsViewModel::class.java).apply { this.movieId = movieId }

}

@Scope
annotation class MovieDetailsScope