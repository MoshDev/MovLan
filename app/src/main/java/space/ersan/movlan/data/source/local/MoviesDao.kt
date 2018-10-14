package space.ersan.movlan.data.source.local

import androidx.room.*
import com.google.gson.Gson
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie
import java.util.*

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
@TypeConverters(GenreTypeConverter::class, DateTypeConverter::class, IntArrayTypeConverter::class)
abstract class MoviesDb : RoomDatabase() {

  companion object {
    const val DB_NAME = "movies_db"
  }

  abstract fun moviesDao(): MoviesDao
}

@Dao
interface MoviesDao {

  @Query("SELECT * FROM movie")
  fun getAll(): List<Movie>

  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(movie: Movie)

}

class GenreTypeConverter {

  private val gson = Gson()

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

  private val gson = Gson()

  @TypeConverter
  fun toArray(value: String?): IntArray? = value?.let { gson.fromJson(value, IntArray::class.java) }

  @TypeConverter
  fun toString(value: IntArray?): String? = value.let { gson.toJson(value) }
}
