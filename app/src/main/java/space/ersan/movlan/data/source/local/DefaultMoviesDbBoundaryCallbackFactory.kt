package space.ersan.movlan.data.source.local

import space.ersan.movlan.data.source.MoviesRepository

class DefaultMoviesDbBoundaryCallbackFactory : MoviesDbBoundaryCallbackFactory {

  override fun createCallback(repository: MoviesRepository): MoviesDbBoundaryCallback {
    return MoviesDbBoundaryCallback(repository)
  }

}