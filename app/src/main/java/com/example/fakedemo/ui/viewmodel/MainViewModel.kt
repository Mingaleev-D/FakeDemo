package com.example.fakedemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakedemo.data.repository.ProductsRepository
import com.example.fakedemo.redux.ApplicationState
import com.example.fakedemo.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

@HiltViewModel
class MainViewModel @Inject constructor(
   private val productsRepository: ProductsRepository,
   val store: Store<ApplicationState>
) : ViewModel() {


   fun refreshProducts() = viewModelScope.launch {
      val products = productsRepository.fetchAllProducts()
      store.update { applicationState ->
         return@update applicationState.copy(
            products = products
         )
      }

   }
}