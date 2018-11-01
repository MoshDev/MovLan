package space.ersan.movlan.data.source.local

import androidx.paging.DataSource
import space.ersan.movlan.data.model.Genre
import space.ersan.movlan.data.model.Movie

interface LocalDataSource {
  fun insertAll(page: Int, movies: List<Movie>)
  fun getMovies(): DataSource.Factory<Int, Movie>
  fun deleteAllMovies()
  fun deleteAllMoviesExcept(pageToKeep: Int)
  fun getGenres(): List<Genre>
  fun insertAllGenres(genres: List<Genre>)
  fun deleteAllGenres()
  fun getMovie(movieId: Int): Movie?
}