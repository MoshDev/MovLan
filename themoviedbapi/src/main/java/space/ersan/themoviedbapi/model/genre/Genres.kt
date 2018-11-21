package space.ersan.themoviedbapi.model.genre

import com.google.gson.annotations.SerializedName

data class Genres(
  @SerializedName("genres")
  val genres: List<Genre>?
)
