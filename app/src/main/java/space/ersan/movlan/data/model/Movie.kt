package space.ersan.movlan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Suppress("ArrayInDataClass")
@Entity(tableName = "movie")
data class Movie(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genres") var genres: List<Genre>? = null,
    @SerializedName("homepage") val homepage: String? = null,
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: Date? = null,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("tagline") val tagline: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("genre_ids") val genreIds: IntArray? = null,
    @SerializedName("revenue") val revenue: Long? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    val page: Int = 1,
    val indexInListing: Int = 0
)