package space.ersan.themoviedbapi.model.error

import com.google.gson.annotations.SerializedName

open class ServerError(
  @SerializedName("status_code")
  val statusCode: Int?,
  @SerializedName("status_message")
  val statusMessage: String?
)