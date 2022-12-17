package com.example.fakedemo.data.repository

import com.example.fakedemo.data.service.ProductsService
import com.example.fakedemo.model.domain.Product
import com.example.fakedemo.model.mapper.ProductMapper
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

class ProductsRepository @Inject constructor(
   private val productsService: ProductsService,
   private val productMapper: ProductMapper
) {

   suspend fun fetchAllProducts(): List<Product> {
      return productsService.fetchAllProducts().body()?.let { productsDto ->
         productsDto.map { productMapper.buildFrom(it) }
      } ?: emptyList()
   }
}