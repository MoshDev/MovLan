package space.ersan.movlan.utils

import androidx.lifecycle.LiveData

sealed class NetworkStatus {
  object Loading : NetworkStatus()
  object Loaded : NetworkStatus()
  class Error(val exception: Exception? = null, val retry: (() -> Unit)? = null) : NetworkStatus()
}

class LiveNetworkStatus : LiveData<NetworkStatus>() {

  init {
    value = NetworkStatus.Loaded
  }

  @Deprecated("not for public use")
  public override fun postValue(value: NetworkStatus?) {
    if (this.value != null && value != null) {
      if (this.value!!::class == value::class) {
        return
      }
    }
    super.postValue(value)
  }

  fun postLoading() {
    postValue(NetworkStatus.Loading)
  }

  fun postLoaded() {
    postValue(NetworkStatus.Loaded)
  }

  fun postError(exception: Exception?, retry: (() -> Unit)?) {
    postValue(NetworkStatus.Error(exception, retry))
  }

  fun postError(retry: (() -> Unit)?) {
    postValue(NetworkStatus.Error(retry = retry))
  }
}