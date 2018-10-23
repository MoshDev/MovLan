package space.ersan.movlan.app.builder

import android.app.Application
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import space.ersan.movlan.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.ersan.movlan.data.model.AppConfig
import space.ersan.movlan.ext.queryInterceptor
import space.ersan.movlan.data.source.remote.MovieDbApi
import java.io.File

@Module
class RetrofitModule {

  @Provides
  @AppScope
  fun provideMovieDbApi(retrofit: Retrofit): MovieDbApi {
    return retrofit.create(MovieDbApi::class.java)
  }

  @Provides
  @AppScope
  fun provideRetrofit(appConfig: AppConfig, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(appConfig.movieDbApiBaseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
  }

  @Provides
  @AppScope
  fun provideOkHttpClient(appConfig: AppConfig, cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
//        .cache(cache)
        .queryInterceptor("api_key", appConfig.movieDbApiKey)
        .addInterceptor(createLoggingInterceptor())
        .build()
  }

  @Provides
  @AppScope
  fun provideOkHttpCache(application: Application, appConfig: AppConfig): Cache {
    return Cache(File(application.cacheDir, "http_cache").apply {
      if (!exists()) mkdirs()
    }, appConfig.httpCacheSize)
  }

  private fun createLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
      if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
      }
    }
  }
}