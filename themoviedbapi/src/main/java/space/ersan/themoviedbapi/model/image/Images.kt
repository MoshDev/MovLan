package space.ersan.themoviedbapi.model.image

import com.google.gson.annotations.SerializedName

data class Images(
  @SerializedName("id")
  val id: Int?,
  @SerializedName("backdrops")
  val backdrops: List<Backdrop>?,
  @SerializedName("posters")
  val posters: List<Poster>?
)