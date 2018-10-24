package space.ersan.movlan.ext

import java.text.SimpleDateFormat
import java.util.*

object MovieDateParser {

  private val MOVIE_DATE_FORMAT by lazy {
    SimpleDateFormat("yyyy-MM-dd", Locale.US)
  }

  fun parseJsonDate(date: String?): Date? {
    if (!date.isNullOrBlank()) {
      return MOVIE_DATE_FORMAT.parse(date)
    }
    return null
  }
}

private val MOVIE_YEAR_DATE_FORMAT by lazy {
  SimpleDateFormat("yyyy", Locale.US)
}

fun Date.toYear(): String = MOVIE_YEAR_DATE_FORMAT.format(this)
