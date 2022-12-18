package com.example.fakedemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fakedemo.R
import com.example.fakedemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private val binding by lazy {
      ActivityMainBinding.inflate(layoutInflater)
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      val appBarConfiguration = AppBarConfiguration(
         topLevelDestinationIds = setOf(
            R.id.productsListFragment,
            R.id.profileFragment
         )
      )
      val navHostFragment =
         supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
      val navController = navHostFragment.navController
      setupActionBarWithNavController(navController,appBarConfiguration)

      NavigationUI.setupWithNavController(binding.btnNavView,navController)
   }
}