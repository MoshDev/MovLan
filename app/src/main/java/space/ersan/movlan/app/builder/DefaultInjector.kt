package space.ersan.movlan.app.builder

import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.AppConfig
import space.ersan.movlan.data.source.local.DaggerConvertersComponent
import space.ersan.movlan.data.source.local.GenreTypeConverter
import space.ersan.movlan.data.source.local.IntArrayTypeConverter
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.details.builder.DaggerMovieDetailsComponent
import space.ersan.movlan.details.builder.MovieDetailsModule
import space.ersan.movlan.home.HomeActivity
import space.ersan.movlan.home.builder.DaggerHomeComponent
import space.ersan.movlan.home.builder.HomeComponent
import space.ersan.movlan.home.builder.HomeModule
import space.ersan.movlan.movie.MovieListingFragment
import space.ersan.movlan.movie.builder.DaggerMovieListingComponent
import space.ersan.movlan.movie.builder.MovieListingModule
import space.ersan.movlan.search.MovieSearchFragment
import space.ersan.movlan.search.builder.DaggerMovieSearchComponent
import space.ersan.movlan.search.builder.MovieSearchModule

class DefaultInjector(movlanApp: MovlanApp, config: AppConfig) : Injector {

  private val appComponent: AppComponent = DaggerAppComponent.builder()
    .appModule(AppModule(movlanApp))
    .appConfigModule(AppConfigModule(config))
    .gsonModule(GsonModule())
    .imageLoaderModule(ImageLoaderModule())
    .retrofitModule(RetrofitModule())
    .build()

  override fun inject(homeActivity: HomeActivity): HomeComponent {
    val component = DaggerHomeComponent.builder()
      .appComponent(appComponent)
      .homeModule(HomeModule(homeActivity))
      .build()!!
    component.inject(homeActivity)
    return component
  }

  override fun inject(detailsActivity: MovieDetailsActivity) =
    DaggerMovieDetailsComponent.builder()
      .appComponent(appComponent)
      .movieDetailsModule(MovieDetailsModule(detailsActivity))
      .build()
      .inject(detailsActivity)

  override fun inject(fragment: MovieListingFragment, homeComponent: HomeComponent) =
    DaggerMovieListingComponent.builder()
      .movieListingModule(MovieListingModule(fragment))
      .homeComponent(homeComponent)
      .build()
      .inject(fragment)

  override fun inject(fragment: MovieSearchFragment, homeComponent: HomeComponent) =
    DaggerMovieSearchComponent.builder()
      .homeComponent(homeComponent)
      .movieSearchModule(MovieSearchModule(fragment))
      .build()
      .inject(fragment)

  override fun inject(converter: GenreTypeConverter) =
    DaggerConvertersComponent.builder()
      .appComponent(appComponent)
      .build()
      .inject(converter)

  override fun inject(converter: IntArrayTypeConverter) =
    DaggerConvertersComponent.builder()
      .appComponent(appComponent)
      .build()
      .inject(converter)
}