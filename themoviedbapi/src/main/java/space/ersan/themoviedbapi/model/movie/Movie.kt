package space.ersan.themoviedbapi.model.movie

import com.google.gson.annotations.SerializedName
import space.ersan.themoviedbapi.model.collection.BelongsToCollection
import space.ersan.themoviedbapi.model.credit.Credits
import space.ersan.themoviedbapi.model.genre.Genre
import space.ersan.themoviedbapi.model.image.Images
import space.ersan.themoviedbapi.model.language.SpokenLanguage
import space.ersan.themoviedbapi.model.production.ProductionCompany
import space.ersan.themoviedbapi.model.production.ProductionCountry
import space.ersan.themoviedbapi.model.review.Reviews
import space.ersan.themoviedbapi.model.similar.Similar
import space.ersan.themoviedbapi.model.video.Videos

data class Movie(
  @SerializedName("adult")
  val adult: Boolean?,
  @SerializedName("backdrop_path")
  val backdropPath: String?,
  @SerializedName("belongs_to_collection")
  val belongsToCollection: BelongsToCollection?,
  @SerializedName("budget")
  val budget: Int?,
  @SerializedName("credits")
  val credits: Credits?,
  @SerializedName("genres")
  val genres: List<Genre?>?,
  @SerializedName("homepage")
  val homepage: String?,
  @SerializedName("id")
  val id: Int?,
  @SerializedName("images")
  val images: Images?,
  @SerializedName("imdb_id")
  val imdbId: String?,
  @SerializedName("original_language")
  val originalLanguage: String?,
  @SerializedName("original_title")
  val originalTitle: String?,
  @SerializedName("overview")
  val overview: String?,
  @SerializedName("popularity")
  val popularity: Double?,
  @SerializedName("poster_path")
  val posterPath: String?,
  @SerializedName("production_companies")
  val productionCompanies: List<ProductionCompany?>?,
  @SerializedName("production_countries")
  val productionCountries: List<ProductionCountry?>?,
  @SerializedName("release_date")
  val releaseDate: String?,
  @SerializedName("revenue")
  val revenue: Int?,
  @SerializedName("reviews")
  val reviews: Reviews?,
  @SerializedName("runtime")
  val runtime: Int?,
  @SerializedName("similar")
  val similar: Similar?,
  @SerializedName("spoken_languages")
  val spokenLanguages: List<SpokenLanguage?>?,
  @SerializedName("status")
  val status: String?,
  @SerializedName("tagline")
  val tagline: String?,
  @SerializedName("title")
  val title: String?,
  @SerializedName("video")
  val video: Boolean?,
  @SerializedName("videos")
  val videos: Videos?,
  @SerializedName("vote_average")
  val voteAverage: Double?,
  @SerializedName("vote_count")
  val voteCount: Int?
)