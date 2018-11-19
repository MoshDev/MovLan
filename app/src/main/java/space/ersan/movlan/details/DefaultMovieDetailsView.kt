package space.ersan.movlan.details

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import space.ersan.movlan.R
import space.ersan.movlan.common.NativeView
import space.ersan.movlan.data.model.Movie
import space.ersan.movlan.ext.toYear
import space.ersan.movlan.image.ImageLoader
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("ViewConstructor")
class DefaultMovieDetailsView(
  context: Context,
  private val backdropLoader: ImageLoader.Backdrop,
  private val posterLoader: ImageLoader.Poster
) : FrameLayout(context), NativeView, MovieDetailsView {

  private val backdropImageView: ImageView
  private val thumbnailImageView: ImageView
  private val titleTextView: TextView
  private val rateTextView: TextView
  private val yearTextView: TextView
  private val genresTextView: TextView
  private val descriptionTextView: TextView
  private val homepageButton: Button
  private val runtimeTextView: TextView
  private val revenueTextView: TextView
  private val languageTextView: TextView
  private lateinit var movie: Movie

  init {
    View.inflate(context, R.layout.view_movie_details, this)
    backdropImageView = findViewById(R.id.backdropImageView)
    thumbnailImageView = findViewById(R.id.thumbnailImageView)
    titleTextView = findViewById(R.id.titleTextView)
    rateTextView = findViewById(R.id.movieRatingTextView)
    yearTextView = findViewById(R.id.yearTextView)
    genresTextView = findViewById(R.id.genresTextView)
    descriptionTextView = findViewById(R.id.descriptionTextView)
    homepageButton = findViewById(R.id.homepageButton)
    runtimeTextView = findViewById(R.id.runtimeTextView)
    revenueTextView = findViewById(R.id.revenueTextView)
    languageTextView = findViewById(R.id.languageTextView)
  }

  override fun setMovie(movie: Movie) {
    this.movie = movie
    backdropLoader.loadImage(backdropImageView, movie)
    posterLoader.loadImage(thumbnailImageView, movie)
    titleTextView.text = movie.title
    yearTextView.text = movie.releaseDate?.toYear() ?: ""
    rateTextView.text = movie.voteAverage?.toString() ?: ":| "
    genresTextView.text = movie.genres?.joinToString(" | ") { genre ->
      genre.name ?: ""
    }

    descriptionTextView.text = movie.overview
    homepageButton.isVisible = !movie.homepage.isNullOrBlank()

    runtimeTextView.isVisible = movie.runtime != null
    movie.runtime?.let {
      runtimeTextView.text = resources.getString(R.string.movie_runtime, it / 60, it % 60)
    }

    revenueTextView.isVisible = movie.revenue != null
    movie.revenue?.let {
      revenueTextView.text = resources.getString(
        R.string.movie_revenue,
        NumberFormat.getCurrencyInstance(Locale.US).apply {
          maximumFractionDigits = 0
        }.format(it)
      )
    }

    languageTextView.isVisible = !movie.originalLanguage.isNullOrBlank()
    movie.originalLanguage?.let {
      languageTextView.text = resources.getString(
        R.string.movie_language,
        Locale(it).displayLanguage ?: resources.getString(R.string.unknown_language)
      )
    }
  }

  override fun observeHomePageButtonClicks(clb: (String?) -> Unit) {
    homepageButton.setOnClickListener {
      clb(movie.homepage)
    }
  }

  override fun getView() = this
}