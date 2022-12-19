package com.example.fakedemo.redux

import com.example.fakedemo.model.domain.Filter
import com.example.fakedemo.model.domain.Product

data class ApplicationState(
   val products: List<Product> = emptyList(),
   val favoriteProductIds:Set<Int> = emptySet(),
   val expandedProductIds:Set<Int> = emptySet(),
   val inCartProductIds:Set<Int> = emptySet(),
   val productFilterInfo: ProductFilterInfo = ProductFilterInfo(),
){
   data class ProductFilterInfo(
      val filters: Set<Filter> = emptySet(),
      val selectedFilter: Filter? = null
   )
}
