package space.ersan.themoviedbapi.model.config

import com.google.gson.annotations.SerializedName

data class Configurations(
  @SerializedName("change_keys")
  val changeKeys: List<String?>?,
  @SerializedName("images")
  val images: Images?
)