package space.ersan.movlan.data.source.local

import androidx.room.*
import com.google.gson.Gson
import space.ersan.movlan.data.model.Movie

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
@TypeConverters(GenreTypeConverter::class)
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
  fun toList(value: String?): List<Movie.Genre>? = value?.let {
    gson.fromJson(value, Array<Movie.Genre>::class.java)
        .toMutableList()
  }

  @TypeConverter
  fun toString(value: List<Movie.Genre>?): String? = value?.let { gson.toJson(value.toTypedArray()) }
}