package space.ersan.movlan.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.ersan.movlan.app.builder.Movlan
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

  @Inject
  lateinit var homePresenter: HomePresenter

  @Inject
  lateinit var homeView: HomeView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Movlan.injector.inject(this)
    setContentView(homeView)
    homePresenter.onCreate(savedInstanceState)
  }

}
