package com.example.fakedemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakedemo.data.repository.ProductsRepository
import com.example.fakedemo.model.domain.Filter
import com.example.fakedemo.redux.ApplicationState
import com.example.fakedemo.redux.Store
import com.example.fakedemo.ui.FilterGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

@HiltViewModel
class ProductsListViewModel @Inject constructor(
   private val productsRepository: ProductsRepository,
   val store: Store<ApplicationState>,
   private val filterGenerator: FilterGenerator
) : ViewModel() {


   fun refreshProducts() = viewModelScope.launch {
      val products = productsRepository.fetchAllProducts()
      val filters: Set<Filter> = filterGenerator.generateFrom(products)
      store.update { applicationState ->
         return@update applicationState.copy(
            products = products,
            productFilterInfo = ApplicationState.ProductFilterInfo(
               filters = filters,
               selectedFilter = null
            )
         )
      }

   }
}