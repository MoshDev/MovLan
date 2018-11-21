package space.ersan.themoviedbapi.model.similar

import com.google.gson.annotations.SerializedName

data class Similar(
  @SerializedName("page")
  val page: Int?,
  @SerializedName("results")
  val results: List<SimilarMovie>?,
  @SerializedName("total_pages")
  val totalPages: Int?,
  @SerializedName("total_results")
  val totalResults: Int?
)