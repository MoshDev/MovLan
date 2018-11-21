package space.ersan.themoviedbapi.model.movie

import com.google.gson.annotations.SerializedName
import java.util.Collections

data class Movies(
  @SerializedName("page")
  val page: Int?,
  @SerializedName("reviews")
  val movies: List<Movie>?,
  @SerializedName("total_pages")
  val totalPages: Int?,
  @SerializedName("total_results")
  val totalResults: Int?
) : Iterable<Movie> {
  override fun iterator(): Iterator<Movie> = movies?.iterator() ?: Collections.emptyIterator()
  operator fun get(index: Int) = movies?.get(index)
}