package space.ersan.movlan.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.ersan.movlan.R
import space.ersan.movlan.app.ComponentProvider
import space.ersan.movlan.app.Movlan
import space.ersan.movlan.home.builder.HomeComponent

class HomeActivity : AppCompatActivity(), ComponentProvider<HomeComponent> {

  lateinit var homeComponent: HomeComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeComponent = Movlan.injector.inject(this)

    setContentView(R.layout.view_home)

    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
    NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
  }

  override fun getComponent() = homeComponent
}
