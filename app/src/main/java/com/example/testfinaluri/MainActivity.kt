package com.example.testfinaluri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        supportActionBar?.hide()

        navView = findViewById(R.id.navView)

        val controller = findNavController(R.id.fragment_container)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.save,
                R.id.profile,
                R.id.password
            )
        )
        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)


        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home -> showBottomNav()
                R.id.save -> showBottomNav()
                R.id.profile -> showBottomNav()
                R.id.password -> showBottomNav()
                else -> hideBottomNav()
            }
        }



    }

    private fun showBottomNav() {
        navView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        navView.visibility = View.GONE

    }

}