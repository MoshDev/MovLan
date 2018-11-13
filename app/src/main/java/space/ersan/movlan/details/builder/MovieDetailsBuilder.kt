package space.ersan.movlan.details.builder

import androidx.lifecycle.ViewModelProviders
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.movlan.app.builder.AppComponent
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.details.*
import space.ersan.movlan.image.ImageLoader
import space.ersan.movlan.viewmodel.MovlanViewModelFactory
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [MovieDetailsModule::class])
@MovieDetailsScope
interface MovieDetailsComponent {
  fun inject(activity: MovieDetailsActivity)
}

@Module
class MovieDetailsModule(private val activity: MovieDetailsActivity) {

  @Provides
  @MovieDetailsScope
  fun provideView(posterLoader: ImageLoader.Poster, backdropLoader: ImageLoader.Backdrop): MovieDetailsView {
    return DefaultMovieDetailsView(activity, backdropLoader, posterLoader)
  }

  @Provides
  @MovieDetailsScope
  fun provideNativeView(viewMovie: MovieDetailsView) = viewMovie as NativeView

  @Provides
  @MovieDetailsScope
  fun provideViewModel(factory: MovlanViewModelFactory): MovieDetailsViewModel =
      ViewModelProviders.of(
          activity,
          factory)
          .get(DefaultMovieDetailsViewModel::class.java)
}

@Scope
annotation class MovieDetailsScope