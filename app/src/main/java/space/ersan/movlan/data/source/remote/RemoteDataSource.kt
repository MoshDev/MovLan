package space.ersan.movlan.data.source.remote

import space.ersan.movlan.data.model.GenreList
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.data.model.MovieList
import space.ersan.movlan.utils.Maybe

interface RemoteDataSource {
  suspend fun getPopularMovies(page: Int): Maybe<MovieList>
  suspend fun getMovieDetails(movieId: Int): Maybe<Movie>
  suspend fun getGenres(): Maybe<GenreList>
  suspend fun search(query: String, page: Int): Maybe<MovieList>
}