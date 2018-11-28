package space.ersan.movlan.app.builder

import space.ersan.movlan.data.source.local.GenreTypeConverter
import space.ersan.movlan.data.source.local.IntArrayTypeConverter
import space.ersan.movlan.search.MovieSearchFragment
import space.ersan.movlan.ui.details.MovieDetailsActivity
import space.ersan.movlan.ui.feed.FeedFragment
import space.ersan.movlan.ui.home.HomeActivity
import space.ersan.movlan.ui.home.builder.HomeComponent
import space.ersan.movlan.ui.movie.MovieListingFragment

interface Injector {
  fun inject(homeActivity: HomeActivity): HomeComponent
  fun inject(detailsActivity: MovieDetailsActivity)
  fun inject(fragment: MovieListingFragment, homeComponent: HomeComponent)
  fun inject(fragment: MovieSearchFragment, homeComponent: HomeComponent)
  fun inject(converter: GenreTypeConverter)
  fun inject(converter: IntArrayTypeConverter)
  fun inject(feedFragment: FeedFragment, homeComponent: HomeComponent)
}