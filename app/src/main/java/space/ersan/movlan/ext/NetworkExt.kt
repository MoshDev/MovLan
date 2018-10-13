package space.ersan.movlan.ext

import okhttp3.OkHttpClient

fun OkHttpClient.Builder.headerInterceptor(name: String, value: String): OkHttpClient.Builder = this.addInterceptor {
  val request = it.request()
      .newBuilder()
      .header(name, value)
      .build()
  it.proceed(request)
}

fun OkHttpClient.Builder.queryInterceptor(name: String, value: String): OkHttpClient.Builder = this.addInterceptor {
  val request = it.request()
      .newBuilder()
      .url(it.request().url().newBuilder().addEncodedQueryParameter(name, value).build())
      .build()
  it.proceed(request)
}