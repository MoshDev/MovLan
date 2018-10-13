package space.ersan.movlan.ext

import android.os.Build
import android.view.View
import androidx.annotation.DrawableRes


fun View.loadForeground(@DrawableRes resId:Int){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    this.foreground = context.getDrawable(resId)
  }
}

fun View.removeForeground(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    this.foreground = null
  }
}