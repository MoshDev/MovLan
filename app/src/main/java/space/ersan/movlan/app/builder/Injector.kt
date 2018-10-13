package space.ersan.movlan.app.builder

import space.ersan.movlan.app.MovlanApp
import space.ersan.movlan.data.model.AppConfig
import space.ersan.movlan.home.HomeActivity
import space.ersan.movlan.home.builder.DaggerHomeComponent
import space.ersan.movlan.home.builder.HomeModule

class Injector(movlanApp: MovlanApp, config: AppConfig) {

  private val appComponent: AppComponent = DaggerAppComponent.builder()
      .appModule(AppModule(movlanApp))
      .appConfigModule(AppConfigModule(config))
      .gsonModule(GsonModule())
      .imageLoaderModule(ImageLoaderModule())
      .retrofitModule(RetrofitModule())
      .build()

  fun inject(homeActivity: HomeActivity) = DaggerHomeComponent.builder()
      .appComponent(appComponent)
      .homeModule(HomeModule(homeActivity))
      .build()
      .inject(homeActivity)

}