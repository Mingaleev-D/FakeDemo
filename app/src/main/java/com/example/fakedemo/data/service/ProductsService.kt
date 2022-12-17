package com.example.fakedemo.data.service

import com.example.fakedemo.model.networkDto.ProductResponseDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

interface ProductsService {

   @GET("products")
   suspend fun fetchAllProducts(): Response<List<ProductResponseDto>>
}