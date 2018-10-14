package space.ersan.movlan.common.view


import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatImageView
import space.ersan.movlan.R

/**
 * Maintains an aspect ratio based on either width or height. Disabled by default.
 */
class AspectRatioImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(
    context,
    attrs) {

  private val aspectRatio: Float
  private val aspectRatioEnabled: Boolean
  private val dominantMeasurement: Int

  init {

    val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
    aspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_ASPECT_RATIO)
    aspectRatioEnabled = a.getBoolean(R.styleable.AspectRatioImageView_aspectRatioEnabled,
        DEFAULT_ASPECT_RATIO_ENABLED)
    dominantMeasurement = a.getInt(R.styleable.AspectRatioImageView_dominantMeasurement,
        DEFAULT_DOMINANT_MEASUREMENT)
    a.recycle()
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    if (!aspectRatioEnabled) return

    val newWidth: Int
    val newHeight: Int
    when (dominantMeasurement) {
      MEASUREMENT_WIDTH -> {
        newWidth = measuredWidth
        newHeight = (newWidth * aspectRatio).toInt()
      }

      MEASUREMENT_HEIGHT -> {
        newHeight = measuredHeight
        newWidth = (newHeight * aspectRatio).toInt()
      }

      else -> throw IllegalStateException("Unknown measurement with ID $dominantMeasurement")
    }

    setMeasuredDimension(newWidth, newHeight)
  }

  companion object {
    // NOTE: These must be kept in sync with the AspectRatioImageView attributes in attrs.xml.
    private const val MEASUREMENT_WIDTH = 0
    private const val MEASUREMENT_HEIGHT = 1

    private const val DEFAULT_ASPECT_RATIO = 1f
    private const val DEFAULT_ASPECT_RATIO_ENABLED = false
    private const val DEFAULT_DOMINANT_MEASUREMENT = MEASUREMENT_WIDTH
  }
}

