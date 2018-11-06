package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.utils.LiveNetworkStatus

interface MoviesDbBoundaryCallbackFactory {
  fun createCallback(repository: MoviesRepository, networkStatus: LiveNetworkStatus): MoviesDbBoundaryCallback
}