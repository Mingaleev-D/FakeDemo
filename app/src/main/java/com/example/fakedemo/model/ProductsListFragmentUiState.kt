package com.example.fakedemo.model

import com.example.fakedemo.model.uiFilter.UiFilter
import com.example.fakedemo.model.uiProduct.UiProduct

data class ProductsListFragmentUiState(
   val filters:Set<UiFilter>,
   val products:List<UiProduct>
)
