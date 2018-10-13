package space.ersan.movlan.app.builder

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.local.MoviesDb

@Module
class DatabaseModule {

  @AppScope
  @Provides
  fun moviesDb(application: Application) = Room.databaseBuilder(application,
      MoviesDb::class.java,
      MoviesDb.DB_NAME).build()

  @AppScope
  @Provides
  fun moviesDao(db: MoviesDb) = db.moviesDao()

}