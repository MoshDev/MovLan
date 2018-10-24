package space.ersan.movlan.app.builder

import android.app.Application
import dagger.Module
import dagger.Provides
import space.ersan.movlan.image.ImageLoader

@Module
class ImageLoaderModule {

  @AppScope
  @Provides
  fun thumbnailLoader(application: Application)  = ImageLoader.Poster(application)

  @AppScope
  @Provides
  fun posterLoader(application: Application)  = ImageLoader.Backdrop(application)

}