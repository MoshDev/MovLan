package space.ersan.movlan.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import space.ersan.movlan.R

class AspectRatioImageView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

  private val ratio: Float

  init {
    val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
    ratio = a.getFloat(R.styleable.AspectRatioImageView_ari_ratio, 1F)
    a.recycle()
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    var width = measuredWidth
    var height = measuredHeight

    if (width == 0 && height == 0) {
      return
    }

    if (width > 0) {
      height = (width * ratio).toInt()
    } else {
      width = (height / ratio).toInt()
    }

    setMeasuredDimension(width, height)
  }
}