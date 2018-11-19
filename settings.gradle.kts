pluginManagement {
  repositories {
    gradlePluginPortal()
    maven("https://jcenter.bintray.com/")
    maven("https://maven.google.com")
    maven("https://plugins.gradle.org/m2/")
    maven("https://maven.fabric.io/public")
  }

  resolutionStrategy {
    eachPlugin {
      when (requested.id.id) {
        "com.android.application" -> useModule("com.android.tools.build:gradle:${requested.version}")
        "kotlin-android-extensions" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
        "com.google.gms.oss.licenses.plugin" -> useModule("com.google.gms:oss-licenses:${requested.version}")
        "org.jlleitschuh.gradle.ktlint" -> useModule("org.jlleitschuh.gradle:ktlint-gradle:${requested.version}")
        "io.fabric" -> useModule("io.fabric.tools:gradle:${requested.version}")
      }
    }
  }
}

include(":app", ":themoviedbapi")
