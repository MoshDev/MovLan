package space.ersan.themoviedbapi.model.review

import com.google.gson.annotations.SerializedName

data class Reviews(
  @SerializedName("page")
  val page: Int?,
  @SerializedName("results")
  val results: List<Review>?,
  @SerializedName("total_pages")
  val totalPages: Int?,
  @SerializedName("total_results")
  val totalResults: Int?
)