package space.ersan.movlan.common.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.atomic.AtomicReference

abstract class BaseAdapter<ITEM, VH : BaseViewHolder<ITEM>>(
    var callback: ((ITEM) -> Unit)? = null) : RecyclerView.Adapter<VH>() {

  val items = mutableListOf<ITEM>()

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

  override fun onViewRecycled(holder: VH) = holder.onUnbind()

}

abstract class BaseViewHolder<ITEM>(view: View, var callback: ((ITEM) -> Unit)? = null) : RecyclerView.ViewHolder(view) {

  constructor(parent: ViewGroup, @LayoutRes layoutRes: Int, callback: ((ITEM) -> Unit)? = null) : this(
      LayoutInflater.from(parent.context).inflate(layoutRes, parent, false), callback)

  init {
    itemView.setOnClickListener { onItemViewClicked() }
  }

  protected open var clickable = true
  protected val item = AtomicReference<ITEM>()
  protected var avoidRebinding = true
  protected val context: Context
    get() = itemView.context

  open fun getItem(): ITEM? {
    return item.get()
  }

  fun bind(item: ITEM) {
    with(this@BaseViewHolder.item) {
      if (avoidRebinding && get() == item) {
        return
      } else {
        set(item)
        onBind(item)
      }
    }
  }

  abstract fun onBind(item: ITEM)

  open fun onItemViewClicked() {
    getItem()?.let { it1 ->
      if (clickable) {
        callback?.invoke(it1)
      }
    }
  }

  open fun onUnbind() {}

  fun <T : View> findViewById(@IdRes id: Int): T? = itemView.findViewById(id)

}