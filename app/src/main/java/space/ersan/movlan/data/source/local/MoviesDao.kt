package space.ersan.movlan.data.source.local

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
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

//  @Query("SELECT * FROM movie WHERE page=:page ORDER BY indexInListing ASC")
//  fun getMoviesByPage(page: Int): DataSource.Factory<Int, Movie>

  @Query("SELECT * FROM movie WHERE page=:page ORDER BY indexInListing ASC")
  fun getMoviesByPage(page: Int): DataSource.Factory<Int,Movie>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(movies: List<Movie>)

  @Query("DELETE FROM movie")
  fun deleteAll()

}

