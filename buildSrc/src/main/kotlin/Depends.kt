object Depends {

  object Kotlin {
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.version}"

    object Coroutines {
      val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
      val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    }

    object Coroutines2 {
      val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
      val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    }
  }

  object Support {
    val appcompat = "androidx.appcompat:appcompat:${Versions.Support.appcompat}"
    val material = "com.google.android.material:material:${Versions.Support.material}"
    val vectordrawable = "androidx.vectordrawable:vectordrawable:${Versions.Support.vectordrawable}"
    val cardview = "androidx.cardview:cardview:${Versions.Support.cardview}"
    val ktxcore = "androidx.core:core-ktx:${Versions.Support.ktx}"
    val constraintlayout =
      "androidx.constraintlayout:constraintlayout:${Versions.Support.constraintlayout}"
  }

  object Room {
    val runtime = "androidx.room:room-runtime:${Versions.Support.room}"
    val compiler = "androidx.room:room-compiler:${Versions.Support.room}"
  }

  object LifeCycle {
    val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Support.lifecycle}"
    val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Support.lifecycle}"
    val livedata = "androidx.lifecycle:lifecycle-livedata:${Versions.Support.lifecycle}"
    val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.Support.lifecycle}"
    val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.Support.lifecycle}"
  }

  object Paging {
    val runtime = "androidx.paging:paging-runtime-ktx:${Versions.Support.paging}"
  }

  object Navigation {
    val fragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.Support.navigation}"
    val ui = "android.arch.navigation:navigation-ui-ktx:${Versions.Support.navigation}"
  }

  object Network {
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okhttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
    val gsonconvertor = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}"
    val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.logging}"
    val coroutineadapter =
      "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.Network.coroutinesAdapter}"
  }

  object Glide {
    val runtime = "com.github.bumptech.glide:glide:${Versions.Image.glide}"
    val compiler = "com.github.bumptech.glide:compiler:${Versions.Image.glide}"
  }

  object Dagger {
    val runtime = "com.google.dagger:dagger:${Versions.Dagger.version}"
    val compiler = "com.google.dagger:dagger-compiler:${Versions.Dagger.version}"
  }
}