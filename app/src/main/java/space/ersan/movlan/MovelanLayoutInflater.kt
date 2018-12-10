package space.ersan.movlan

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Keep

@Keep
class MovelanLayoutInflater : MaterialComponentsViewInflater() {

  override fun createView(context: Context?, name: String?, attrs: AttributeSet?): View? {
    println("createView name = [$name]")
    return super.createView(context, name, attrs)
  }
}