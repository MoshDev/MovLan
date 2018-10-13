package space.ersan.movlan.data.model

data class AppConfig(
    val movieDbApiKey: String,
    val movieDbApiBaseUrl: String,
    val httpCacheSize: Long = 1024L * 1024L * 20L /*20MB*/,
    val imagesCacheSize: Long = 1024L * 1024L * 50L /*50MB*/
)