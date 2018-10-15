package space.ersan.movlan.ext

import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie

fun Movie.getEnrichedGenres(listOfGenres: List<Genre>?): List<Genre> {
  if (listOfGenres != null && listOfGenres.isNotEmpty() && genreIds != null && genreIds.isNotEmpty()) {
    return genreIds.map { id -> listOfGenres.firstOrNull { g -> g.id == id } }
        .filterNotNull()
  }
  return emptyList()
}