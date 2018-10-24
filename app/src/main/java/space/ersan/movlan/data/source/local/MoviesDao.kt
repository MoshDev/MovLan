package space.ersan.movlan.data.source.local

import androidx.paging.DataSource
import androidx.room.*
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie

@Database(entities = [Movie::class, Genre::class], version = 1, exportSchema = false)
@TypeConverters(GenreTypeConverter::class, DateTypeConverter::class, IntArrayTypeConverter::class)
abstract class MoviesDb : RoomDatabase() {

  companion object {
    const val DB_NAME = "movies_db"
  }

  abstract fun moviesDao(): MoviesDao
}

@Dao
interface MoviesDao {

  @Query("SELECT * FROM movie ORDER BY indexInListing ASC")
  fun getMovies(): DataSource.Factory<Int, Movie>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovies(movies: List<Movie>)

  @Query("DELETE FROM movie")
  fun deleteAllMovies()

  @Query("DELETE FROM movie WHERE page<> :pageToKeep")
  fun deleteAllMoviesExcept(pageToKeep: Int)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertGenres(genres: List<Genre>)

  @Query("SELECT * FROM genre")
  fun getGenres(): List<Genre>

  @Query("DELETE FROM genre")
  fun deleteAllGenres()

  @Query("SELECT * From movie WHERE id== :movieId LIMIT 1")
  fun getMovie(movieId: Int): Movie?
}

