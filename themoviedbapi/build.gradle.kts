import java.util.Properties

plugins {
  id("com.android.library") version Versions.Android.gradle
  kotlin("android") version Versions.Kotlin.version
  id("kotlin-android-extensions") version Versions.Kotlin.version
  id("org.jlleitschuh.gradle.ktlint") version Versions.Ktlint.plugin
}

android {
  compileSdkVersion(Versions.Android.compileSdk)
  defaultConfig {
    minSdkVersion(Versions.Android.minSdk)
    targetSdkVersion(Versions.Android.targetSdk)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {

  implementation(Depends.Kotlin.stdlib)
  implementation(Depends.Kotlin.Coroutines.core)
  implementation(Depends.Kotlin.Coroutines.android)

  implementation(Depends.Network.okhttp)
  implementation(Depends.Network.retrofit)
  implementation(Depends.Network.gsonconvertor)
  implementation(Depends.Network.logging)
  implementation(Depends.Network.coroutineadapter)

}