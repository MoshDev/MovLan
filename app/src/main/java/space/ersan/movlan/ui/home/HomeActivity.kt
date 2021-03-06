package space.ersan.movlan.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.ersan.movlan.R
import space.ersan.movlan.app.ComponentProvider
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.ui.home.builder.HomeComponent

class HomeActivity : AppCompatActivity(), ComponentProvider<HomeComponent> {

  lateinit var homeComponent: HomeComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeComponent = Movlan.injector.inject(this)

    setContentView(R.layout.view_home)

    val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHost
    NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
  }

  override fun getComponent() = homeComponent
}