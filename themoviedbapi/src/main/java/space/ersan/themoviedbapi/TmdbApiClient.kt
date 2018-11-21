package space.ersan.themoviedbapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.ersan.themoviedbapi.model.config.Configurations
import space.ersan.themoviedbapi.model.genre.Genres
import space.ersan.themoviedbapi.model.movie.Movie
import space.ersan.themoviedbapi.model.movie.Movies
import space.ersan.themoviedbapi.utils.MovieDateParser
import java.util.Date

class TmdbApiClient(
  private val apiKey: String,
  private val dispatcher: CoroutineDispatcher,
  private val tmdbApi: TmdbApi
) {

  companion object {
    fun newDefaultInstance(): TmdbApiClient {

      fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
          if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
          }
        }
      }

      fun getGson(): Gson {
        val builder = GsonBuilder()
        builder.setLenient()

        builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
          when (json.asJsonPrimitive.isString) {
            true -> MovieDateParser.parseJsonDate(json.asJsonPrimitive.asString)
            else -> null
          }
        })
        return builder.create()
      }

      val key = "5d66a45677199ff38b6070adc8141e75"
      val dispatcher = Dispatchers.IO
      val baseUrl = "https://api.themoviedb.org/3/"
      val okHttpClient = OkHttpClient.Builder()
//        .cache(cache) // Disabled to demonstrate the save state
        .addInterceptor(createLoggingInterceptor())
        .build()

      val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

      val tmdbApi = retrofit.create(TmdbApi::class.java)


      return TmdbApiClient(key, dispatcher, tmdbApi)
    }
  }

  suspend fun getApiConfigurations(): Configurations =
    withContext(dispatcher) { tmdbApi.getApiConfigurations(apiKey).await() }

  suspend fun getMovieDetails(
    movieId: Int,
    appendToResponse: String?
  ): Movie = withContext(dispatcher) {
    tmdbApi.getMovieDetails(
      apiKey,
      null,
      appendToResponse,
      movieId
    ).await()
  }

  suspend fun getMovieGenres(): Genres =
    withContext(dispatcher) { tmdbApi.getMovieGenres(apiKey).await() }

  suspend fun getNowPlayingMovies(
    page: Int
  ): Movies = withContext(dispatcher) { tmdbApi.getNowPlayingMovies(apiKey, page).await() }

  suspend fun getUpcomingMovies(
    page: Int
  ): Movies = withContext(dispatcher) { tmdbApi.getUpcomingMovies(apiKey, page).await() }

  suspend fun getPopularMovies(
    page: Int
  ): Movies = withContext(dispatcher) { tmdbApi.getPopularMovies(apiKey, page).await() }

  suspend fun search(
    query: String,
    page: Int
  ): Movies = withContext(dispatcher) { tmdbApi.search(apiKey, query, page, false).await() }
}
