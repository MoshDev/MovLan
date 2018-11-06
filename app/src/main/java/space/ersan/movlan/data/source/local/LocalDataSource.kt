package space.ersan.movlan.data.source.local

import androidx.paging.DataSource
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie

interface LocalDataSource {
  suspend fun insertAll(page: Int, movies: List<Movie>)
  suspend fun getMovies(): DataSource.Factory<Int, Movie>
  suspend fun deleteAllMovies()
  suspend fun deleteAllMoviesExcept(pageToKeep: Int)
  suspend fun getGenres(): List<Genre>
  suspend fun insertAllGenres(genres: List<Genre>)
  suspend fun deleteAllGenres()
  suspend fun getMovie(movieId: Int): Movie?
}