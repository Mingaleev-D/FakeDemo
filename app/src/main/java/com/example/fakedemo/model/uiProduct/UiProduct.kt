package com.example.fakedemo.model.uiProduct

import com.example.fakedemo.model.domain.Product

data class UiProduct(
   val product:Product,
   val isFavorite:Boolean = false,
   val isExpanded:Boolean = false,
   val isInCart:Boolean = false,
)
