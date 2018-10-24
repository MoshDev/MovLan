package space.ersan.movlan

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.data.source.remote.MovieDbApi

class FakeMoviesApi(
    private val popularMovies: List<Movie>? = null,
    private val movieDetails: Movie? = null,
    private val searchResult: List<Movie>? = null,
    private val genreList: List<Genre>? = null
) : MovieDbApi {

  override fun getPopularMovies(page: Int): Deferred<MovieList> =
      CompletableDeferred(MovieList(1, popularMovies?.size, 1, popularMovies ?: emptyList()))

  override fun getMovieDetails(movieId: Int): Deferred<Movie> =
      CompletableDeferred(movieDetails ?: Movie(id = 1))

  override fun getGenres(): Deferred<GenreList> =
      CompletableDeferred(GenreList(genreList ?: emptyList()))

  override fun search(query: String, page: Int, includeAdult: Boolean) =
      CompletableDeferred(MovieList(1, searchResult?.size, 1, searchResult ?: emptyList()))
}