package space.ersan.movlan.app.builder

import dagger.Module
import dagger.Provides
import space.ersan.movlan.data.source.MoviesRepository
import space.ersan.movlan.movie.usecase.GetPopularMoviesUsecase

@Module
class UseCasesModule {

  @Provides
  fun provideFeckinUSeCase(repository: MoviesRepository): GetPopularMoviesUsecase =
    GetPopularMoviesUsecase(repository)
}