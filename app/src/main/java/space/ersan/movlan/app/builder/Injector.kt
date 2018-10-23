package space.ersan.movlan.app.builder

import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.AppConfig
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.details.builder.DaggerMovieDetailsComponent
import space.ersan.movlan.details.builder.MovieDetailsModule
import space.ersan.movlan.home.HomeActivity
import space.ersan.movlan.home.builder.DaggerHomeComponent
import space.ersan.movlan.home.builder.HomeModule
import space.ersan.movlan.home.movie.MovieListingFragment
import space.ersan.movlan.home.movie.builder.DaggerMovieListingComponent
import space.ersan.movlan.home.movie.builder.MovieListingModule
import space.ersan.movlan.search.MovieSearchActivity
import space.ersan.movlan.search.builder.DaggerMovieSearchComponent
import space.ersan.movlan.search.builder.MovieSearchModule

class Injector(movlanApp: MovlanApp, config: AppConfig) {

  private val appComponent: AppComponent = DaggerAppComponent.builder()
      .appModule(AppModule(movlanApp))
      .appConfigModule(AppConfigModule(config))
      .gsonModule(GsonModule())
      .imageLoaderModule(ImageLoaderModule())
      .retrofitModule(RetrofitModule())
      .build()

  fun inject(homeActivity: HomeActivity) = DaggerHomeComponent.builder()
      .homeModule(HomeModule(homeActivity))
      .build()
      .inject(homeActivity)

  fun inject(detailsActivity: MovieDetailsActivity, movieId: Int) = DaggerMovieDetailsComponent.builder()
      .appComponent(appComponent)
      .movieDetailsModule(MovieDetailsModule(detailsActivity, movieId))
      .build()
      .inject(detailsActivity)

  fun inject(movieSearchActivity: MovieSearchActivity) = DaggerMovieSearchComponent.builder()
      .appComponent(appComponent)
      .movieSearchModule(MovieSearchModule((movieSearchActivity)))
      .build()
      .inject(movieSearchActivity)

  fun inject(fragment: MovieListingFragment) = DaggerMovieListingComponent.builder()
      .appComponent(appComponent)
      .movieListingModule(MovieListingModule(fragment))
      .build()
      .inject(fragment)
}