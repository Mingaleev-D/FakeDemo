package com.example.fakedemo.model.mapper

import com.example.fakedemo.model.domain.Product
import com.example.fakedemo.model.networkDto.ProductResponseDto
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

class ProductMapper @Inject constructor( ) {

   fun buildFrom(productDto: ProductResponseDto): Product {
      return Product(
         category = productDto.category,
         description = productDto.description,
         id = productDto.id,
         image = productDto.image,
         price = BigDecimal(productDto.price).setScale(2,RoundingMode.HALF_UP),
         title = productDto.title
      )
   }
}