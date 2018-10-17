package space.ersan.movlan.data.source.remote

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*
import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList

interface MovieDbApi {

  @GET("movie/popular")
  fun getPopularMovies(@Query("page") page: Int = 1): Deferred<MovieList>

  @GET("movie/{movie_id}")
  fun getMovieDetails(@Path("movie_id") movieId: Int): Deferred<Movie>

  @GET("genre/movie/list")
  fun getGenres(): Deferred<GenreList>

  @GET("search/movie")
  fun search(@Query("query") query: String, @Query("page") page: Int = 1, @Query("include_adult") includeAdult: Boolean = false): Deferred<MovieList>

}