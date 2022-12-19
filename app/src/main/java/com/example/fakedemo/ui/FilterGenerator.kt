package com.example.fakedemo.ui

import com.example.fakedemo.model.domain.Filter
import com.example.fakedemo.model.domain.Product
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 19/12/2022
 */

class FilterGenerator @Inject constructor() {

   fun generateFrom(productsList:List<Product>):Set<Filter>{
      return productsList.groupBy {  it.category }.map { mapEntity ->
         Filter(value = mapEntity.key, displayText = "${mapEntity.key} (${mapEntity.value.size})")
      }.toSet()
   }
}