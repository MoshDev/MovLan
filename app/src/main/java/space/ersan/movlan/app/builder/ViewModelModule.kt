package space.ersan.movlan.app.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import space.ersan.movlan.search.DefaultMovieSearchViewModel
import space.ersan.movlan.ui.details.DefaultMovieDetailsViewModel
import space.ersan.movlan.ui.feed.FeedViewModel
import space.ersan.movlan.ui.movie.DefaultMovieListingViewModel
import space.ersan.movlan.viewmodel.MovlanViewModelFactory
import space.ersan.movlan.viewmodel.ViewModelKey

@Module
abstract class ViewModelModule {

  @Binds
  abstract fun viewModelFactory(instance: MovlanViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(DefaultMovieListingViewModel::class)
  abstract fun provideListingViewModel(instance: DefaultMovieListingViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(DefaultMovieDetailsViewModel::class)
  abstract fun provideDetailsViewModel(instance: DefaultMovieDetailsViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(DefaultMovieSearchViewModel::class)
  abstract fun provideSearchViewModel(instance: DefaultMovieSearchViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(FeedViewModel::class)
  abstract fun provideFeedViewModel(instance: FeedViewModel): ViewModel
}