package space.ersan.movlan.app.builder

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskCache
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideConfiguration : AppGlideModule() {

  private val animationExecutor = GlideExecutor.newAnimationExecutor()
  private var diskCacheFactory: DiskCache.Factory? = null

  override fun applyOptions(context: Context, builder: GlideBuilder) {
    builder.setAnimationExecutor(animationExecutor)
    if (diskCacheFactory == null) {
      diskCacheFactory = InternalCacheDiskCacheFactory(
        context.applicationContext, "images_cached",
        1024 * 1024 * 50
      )
    }
    builder.setDiskCache(diskCacheFactory)
  }
}