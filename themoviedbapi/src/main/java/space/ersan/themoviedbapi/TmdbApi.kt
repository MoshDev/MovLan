package space.ersan.themoviedbapi

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import space.ersan.themoviedbapi.model.config.Configurations
import space.ersan.themoviedbapi.model.genre.Genres
import space.ersan.themoviedbapi.model.movie.Movie
import space.ersan.themoviedbapi.model.movie.Movies

interface TmdbApi {

  @GET("configuration")
  fun getApiConfigurations(@Query("api_key") apiKey: String): Deferred<Configurations>

  @GET("movie/{movie_id}")
  fun getMovieDetails(
    @Query("api_key") apiKey: String,
    @Query("language") language: String? = null,
    @Query("append_to_response") appendToResponse: String? = null,
    @Path("movie_id") movieId: Int
  ): Deferred<Movie>

  @GET("genre/movie/list")
  fun getMovieGenres(@Query("api_key") apiKey: String): Deferred<Genres>

  @GET("movie/now_playing")
  fun getNowPlayingMovies(
    @Query("api_key") apiKey: String,
    @Query("page") page: Int = 1,
    @Query("language") language: String? = null,
    @Query("region") region: String? = null
  ): Deferred<Movies>

  @GET("movie/upcoming")
  fun getUpcomingMovies(
    @Query("api_key") apiKey: String,
    @Query("page") page: Int = 1,
    @Query("language") language: String? = null,
    @Query("region") region: String? = null
  ): Deferred<Movies>

  @GET("movie/popular")
  fun getPopularMovies(
    @Query("api_key") apiKey: String,
    @Query("page") page: Int = 1,
    @Query("language") language: String? = null,
    @Query("region") region: String? = null
  ): Deferred<Movies>

  @GET("search/movie")
  fun search(
    @Query("api_key") apiKey: String, @Query("query") query: String, @Query("page") page: Int = 1, @Query(
      "include_adult"
    ) includeAdult: Boolean = false
  ): Deferred<Movies>
}