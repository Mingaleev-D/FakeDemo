package com.example.fakedemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.airbnb.epoxy.Carousel
import com.example.fakedemo.R
import com.example.fakedemo.databinding.ActivityMainBinding
import com.example.fakedemo.redux.ApplicationState
import com.example.fakedemo.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private val binding by lazy {
      ActivityMainBinding.inflate(layoutInflater)
   }

   @Inject
   lateinit var store: Store<ApplicationState>

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      val appBarConfiguration = AppBarConfiguration(
         topLevelDestinationIds = setOf(
            R.id.productsListFragment,
            R.id.profileFragment,
            R.id.cartFragment
         )
      )
      val navHostFragment =
         supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
      val navController = navHostFragment.navController
      setupActionBarWithNavController(navController, appBarConfiguration)

      NavigationUI.setupWithNavController(binding.btnNavView, navController)

      Carousel.setDefaultGlobalSnapHelperFactory(null)

      store.stateFlow.map { it.inCartProductIds.size }.distinctUntilChanged().asLiveData()
         .observe(this) { numberOfProductsInCart ->
            binding.btnNavView.getOrCreateBadge(R.id.cartFragment).apply {
               number = numberOfProductsInCart
               isVisible = numberOfProductsInCart > 0
            }

         }
   }
}