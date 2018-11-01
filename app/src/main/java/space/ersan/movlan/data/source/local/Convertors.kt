package space.ersan.movlan.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.data.model.Genre
import java.util.*
import javax.inject.Inject

class GenreTypeConverter {

  @Inject
  lateinit var gson: Gson

  init {
    Movlan.injector.inject(this)
  }

  @TypeConverter
  fun toList(value: String?): List<Genre>? = value?.let {
    gson.fromJson(value, Array<Genre>::class.java)
        .toMutableList()
  }

  @TypeConverter
  fun toString(value: List<Genre>?): String? = value?.let { gson.toJson(value.toTypedArray()) }
}


class DateTypeConverter {

  @TypeConverter
  fun toDate(value: Long?): Date? = value?.let { Date(it) }

  @TypeConverter
  fun toLong(value: Date?): Long? = value?.time
}

class IntArrayTypeConverter {

  @Inject
  lateinit var gson: Gson

  init {
    Movlan.injector.inject(this)
  }

  @TypeConverter
  fun toArray(value: String?): IntArray? = value?.let { gson.fromJson(value, IntArray::class.java) }

  @TypeConverter
  fun toString(value: IntArray?): String? = value.let { gson.toJson(value) }
}
