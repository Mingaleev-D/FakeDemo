package com.example.fakedemo.redux

import com.example.fakedemo.model.domain.Product

data class ApplicationState(
   val products: List<Product> = emptyList(),
   val favoriteProductIds:Set<Int> = emptySet()
)
