package com.example.fakedemo.model

import com.example.fakedemo.model.uiFilter.UiFilter
import com.example.fakedemo.model.uiProduct.UiProduct

sealed interface ProductsListFragmentUiState {
   data class Success(
      val filters: Set<UiFilter>,
      val products: List<UiProduct>
   ):ProductsListFragmentUiState

   object Loading:ProductsListFragmentUiState
}