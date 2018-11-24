import java.util.Properties

plugins {
  id("com.android.application") version Versions.Android.gradle
  kotlin("android") version Versions.Kotlin.version
  kotlin("kapt") version Versions.Kotlin.version
  id("kotlin-android-extensions") version Versions.Kotlin.version
  id("org.jlleitschuh.gradle.ktlint") version Versions.Ktlint.plugin
}

android {
  compileSdkVersion(Versions.Android.compileSdk)
  defaultConfig {
    applicationId = "space.ersan.movlan"
    minSdkVersion(Versions.Android.minSdk)
    targetSdkVersion(Versions.Android.targetSdk)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    android.defaultConfig.resValue("string", "movie_db_api_key", getMovieApiKey())
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
    getByName("debug") {
      applicationIdSuffix = ".debug"
      versionNameSuffix = "-debug"

      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
  sourceSets {
    getByName("androidTest").java.srcDirs("src/test-shared/java")
    getByName("test").java.srcDirs("src/test-shared/java")
  }
}

dependencies {

  implementation(project(":themoviedbapi"))

  implementation(Depends.Kotlin.stdlib)
  implementation(Depends.Kotlin.Coroutines.core)
  implementation(Depends.Kotlin.Coroutines.android)

  implementation(Depends.Support.appcompat)
  implementation(Depends.Support.material)
  implementation(Depends.Support.vectordrawable)
  implementation(Depends.Support.cardview)
  implementation(Depends.Support.ktxcore)
  implementation(Depends.Support.constraintlayout)

  implementation(Depends.Room.runtime)
  kapt(Depends.Room.compiler)

  implementation(Depends.LifeCycle.extensions)
  implementation(Depends.LifeCycle.viewmodel)
  implementation(Depends.LifeCycle.livedata)
  implementation(Depends.LifeCycle.runtime)
  kapt(Depends.LifeCycle.compiler)

  implementation(Depends.Paging.runtime)

  implementation(Depends.Navigation.fragment)
  implementation(Depends.Navigation.ui)

  implementation(Depends.Network.okhttp)
  implementation(Depends.Network.retrofit)
  implementation(Depends.Network.gsonconvertor)
  implementation(Depends.Network.logging)
  implementation(Depends.Network.coroutineadapter)

  implementation(Depends.Glide.runtime)
  kapt(Depends.Glide.compiler)

  implementation(Depends.Dagger.runtime)
  kapt(Depends.Dagger.compiler)

  // Android Testing Support Library's runner and rules
  val atslRunnerVersion = "1.1.0-alpha4"
  androidTestImplementation("androidx.test:runner:$atslRunnerVersion")
  androidTestImplementation("androidx.test:rules:$atslRunnerVersion")
  androidTestImplementation("androidx.test.ext:junit:1.0.0")
  androidTestImplementation("androidx.test:core:1.0.0")

  val archCoreVersion = "2.0.0"
  androidTestImplementation("androidx.arch.core:core-testing:$archCoreVersion")

  val junitVersion = "4.12"
  androidTestImplementation("junit:junit:$junitVersion")
  androidTestImplementation("com.squareup.retrofit2:retrofit-mock:${Versions.Network.retrofit}")
  androidTestImplementation("org.mockito:mockito-core:2.23.0")
  androidTestImplementation("org.mockito:mockito-android:2.23.0")

  testImplementation("junit:junit:$junitVersion")
  testImplementation("com.squareup.retrofit2:retrofit-mock:${Versions.Network.retrofit}")
  testImplementation("androidx.arch.core:core-testing:$archCoreVersion")
  testImplementation("org.mockito:mockito-core:2.23.0")

}

fun getMovieApiKey(): String {
  return System.getenv("MOVIE_DB_API_KEY")
    ?: project.rootProject.properties["MOVIE_DB_API_KEY"] as? String
    ?: throw Exception("Movie API key is not set.")
}