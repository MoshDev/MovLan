package space.ersan.movlan.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.ersan.movlan.R
import space.ersan.movlan.app.Movlan
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Movlan.injector.inject(this)

    setContentView(R.layout.view_home)

    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
    NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
  }
}
