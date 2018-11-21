package space.ersan.themoviedbapi.model.image

import com.google.gson.annotations.SerializedName

data class Poster(
  @SerializedName("aspect_ratio")
  val aspectRatio: Double?,
  @SerializedName("file_path")
  val filePath: String?,
  @SerializedName("height")
  val height: Int?,
  @SerializedName("iso_639_1")
  val iso6391: String?,
  @SerializedName("vote_average")
  val voteAverage: Int?,
  @SerializedName("vote_count")
  val voteCount: Int?,
  @SerializedName("width")
  val width: Int?
)