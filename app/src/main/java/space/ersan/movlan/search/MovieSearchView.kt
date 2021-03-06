package space.ersan.movlan.search

import android.content.Context
import androidx.paging.PagedList
import space.ersan.movlan.data.model.Movie

interface MovieSearchView {
  fun setSearchQueryText(query: String?)
  fun setMovies(result: PagedList<Movie>)
  fun observeMovieListClicks(clb: (Context, Movie) -> Unit)
  fun observeSearchQuery(clb: (String) -> Unit)
}