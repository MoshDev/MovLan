package space.ersan.movlan.app.builder

import space.ersan.movlan.data.source.local.GenreTypeConverter
import space.ersan.movlan.data.source.local.IntArrayTypeConverter
import space.ersan.movlan.details.MovieDetailsActivity
import space.ersan.movlan.home.HomeActivity
import space.ersan.movlan.home.builder.HomeComponent
import space.ersan.movlan.movie.MovieListingFragment
import space.ersan.movlan.search.MovieSearchFragment

interface Injector {
  fun inject(homeActivity: HomeActivity): HomeComponent
  fun inject(detailsActivity: MovieDetailsActivity, movieId: Int)
  fun inject(fragment: MovieListingFragment, homeComponent: HomeComponent)
  fun inject(fragment: MovieSearchFragment, homeComponent: HomeComponent)
  fun inject(converter: GenreTypeConverter)
  fun inject(converter: IntArrayTypeConverter)
}