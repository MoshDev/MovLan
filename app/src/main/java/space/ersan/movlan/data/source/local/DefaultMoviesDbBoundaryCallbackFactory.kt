package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.LiveNetworkStatus

class DefaultMoviesDbBoundaryCallbackFactory : MoviesDbBoundaryCallbackFactory {

  override fun createCallback(repository: MoviesRepository, networkStatus: LiveNetworkStatus): MoviesDbBoundaryCallback {
    return MoviesDbBoundaryCallback(repository, networkStatus)
  }

}