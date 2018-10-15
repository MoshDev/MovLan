package space.ersan.movlan.data.model

import com.google.gson.annotations.SerializedName


data class GenreList(
    @SerializedName("genres") val genres: List<Genre>?
)