package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.source.MoviesRepository

interface MoviesDbBoundaryCallbackFactory {
  fun createCallback(repository: MoviesRepository): MoviesDbBoundaryCallback
}