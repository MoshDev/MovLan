package space.ersan.movlan.utils

import androidx.lifecycle.LiveData

sealed class NetworkStatus {
  object Loading : NetworkStatus()
  object Loaded : NetworkStatus()
  class Error(val retry: (() -> Unit)? = null) : NetworkStatus()
}

class LiveNetworkStatus : LiveData<NetworkStatus>() {

  init {
    value = NetworkStatus.Loaded
  }

  public override fun postValue(value: NetworkStatus?) {
    if (this.value != null && value != null) {
      if (this.value!!::class == value::class) {
        return
      }
    }
    super.postValue(value)
  }
}