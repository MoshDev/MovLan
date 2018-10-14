package space.ersan.movlan.ext

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*


object MovieDateParser {

  private val MOVIE_DATE_FORMAT by lazy {
    SimpleDateFormat("yyyy-MM-dd", Locale.US)
  }

  fun parseJsonDate(date: String): Date {
    return MOVIE_DATE_FORMAT.parse(date)
  }
}


private val MOVIE_YEAR_DATE_FORMAT by lazy {
  SimpleDateFormat("yyyy", Locale.US)
}

fun Date.toDeviceDate(context: Context): String =
    android.text.format.DateFormat.getMediumDateFormat(context).format(this)

fun Date.toYear(): String = MOVIE_YEAR_DATE_FORMAT.format(this)
