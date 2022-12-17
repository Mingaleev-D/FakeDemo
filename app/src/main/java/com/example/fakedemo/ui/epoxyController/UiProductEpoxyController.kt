package com.example.fakedemo.ui.epoxyController

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fakedemo.model.uiProduct.UiProduct
import com.example.fakedemo.ui.viewmodel.ProductsListViewModel
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

class UiProductEpoxyController(
   private val viewModel: ProductsListViewModel
) : TypedEpoxyController<List<UiProduct>>() {
   override fun buildModels(data: List<UiProduct>?) {
      if (data.isNullOrEmpty()) {
         repeat(7) {
            val epoxyId = it + 1
            UiProductEpoxyModel(
               uiProduct = null,
               onFavoriteIconClicked = ::onFavoriteIconClicked
            ).id(epoxyId).addTo(this)
         }
         return
      }
      data.forEach { uiProduct ->
         UiProductEpoxyModel(
            uiProduct = uiProduct,
            onFavoriteIconClicked = ::onFavoriteIconClicked
         ).id(uiProduct.product.id).addTo(this)
      }
   }

   private fun onFavoriteIconClicked(selectedProductId: Int) {
      viewModel.viewModelScope.launch {
         viewModel.store.update { currentState ->
            val currentFavoriteIds = currentState.favoriteProductIds
            val newFavoriteIds = if (currentFavoriteIds.contains(selectedProductId)) {
               currentFavoriteIds.filter { it != selectedProductId }.toSet()
            } else {
               currentFavoriteIds + setOf(selectedProductId)
            }
            return@update currentState.copy(favoriteProductIds = newFavoriteIds)
         }
      }
   }
}