package space.ersan.movlan

import space.ersan.movlan.data.model.Movie
import java.util.concurrent.atomic.AtomicInteger

class MoviesFactory {

  private val idGenerator = AtomicInteger(0)
  fun createMovie(title: String, page: Int = 1): Movie {
    val id = idGenerator.incrementAndGet()
    return Movie(
      false,
      null,
      null,
      null,
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      title,
      null,
      null,
      null,
      null,
      null,
      null,
      page,
      id
    )
  }
}