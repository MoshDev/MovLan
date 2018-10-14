package space.ersan.movlan.home

import space.ersan.movlan.data.model.MovieList

class HomeModel(private val viewModel: HomeViewModel) {

  fun getMovies(callback: (MovieList) -> Unit): Unit {

//    moviesRepository.getPopularMovies(1, callback)

    println("Mosh ${viewModel.createTime}")

  }


}