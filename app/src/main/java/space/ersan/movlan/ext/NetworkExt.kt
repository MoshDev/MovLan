package space.ersan.movlan.ext

import okhttp3.OkHttpClient

fun OkHttpClient.Builder.queryInterceptor(name: String, value: String): OkHttpClient.Builder = this.addInterceptor {
  val request = it.request()
      .newBuilder()
      .url(it.request().url().newBuilder().addEncodedQueryParameter(name, value).build())
      .build()
  it.proceed(request)
}