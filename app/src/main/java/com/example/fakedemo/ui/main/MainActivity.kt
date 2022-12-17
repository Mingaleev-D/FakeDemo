package com.example.fakedemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.fakedemo.databinding.ActivityMainBinding
import com.example.fakedemo.model.uiProduct.UiProduct
import com.example.fakedemo.ui.epoxyController.UiProductEpoxyController
import com.example.fakedemo.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private val binding by lazy {
      ActivityMainBinding.inflate(layoutInflater)
   }

   private val viewModel: MainViewModel by lazy {
      ViewModelProvider(this)[MainViewModel::class.java]
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      val controller = UiProductEpoxyController(viewModel)
      binding.epoxyRecyclerView.setController(controller = controller)
      controller.setData(emptyList())

      combine(
         viewModel.store.stateFlow.map { it.products },
         viewModel.store.stateFlow.map { it.favoriteProductIds }
      ) { listOfProducts, setOfFavoriteIds ->
         listOfProducts.map { product ->
            UiProduct(product = product, isFavorite = setOfFavoriteIds.contains(product.id))
         }
      }.distinctUntilChanged().asLiveData().observe(this) { uiProducts ->
         controller.setData(uiProducts)
      }
      viewModel.refreshProducts()

   }
}