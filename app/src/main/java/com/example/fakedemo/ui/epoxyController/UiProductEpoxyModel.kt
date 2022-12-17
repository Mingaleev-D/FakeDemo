package com.example.fakedemo.ui.epoxyController

import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import com.example.fakedemo.R
import com.example.fakedemo.databinding.EpoxyProductItemBinding
import com.example.fakedemo.model.uiProduct.UiProduct
import com.example.fakedemo.ui.epoxy.ViewBindingKotlinModel
import java.text.NumberFormat

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

data class UiProductEpoxyModel(
   val uiProduct: UiProduct?,
   val onFavoriteIconClicked:(Int) -> Unit
) : ViewBindingKotlinModel<EpoxyProductItemBinding>(R.layout.epoxy_product_item) {

   private val currencyFormatter = NumberFormat.getCurrencyInstance()

   override fun EpoxyProductItemBinding.bind() {
      uiProduct?.let { uiProduct ->
         shimmerLayout.stopShimmer()

         productTitleTextView.text = uiProduct.product.title
         productDescriptionTextView.text = uiProduct.product.description
         productCategoryTextView.text = uiProduct.product.category
         productPriceTextView.text = currencyFormatter.format(uiProduct.product.price)

         // Favorite icon
         val imageRes = if (uiProduct.isFavorite) {
            R.drawable.ic_round_favorite_24
         } else {
            R.drawable.ic_round_favorite_border_24
         }
         favoriteImageView.setIconResource(imageRes)
         //listener
         favoriteImageView.setOnClickListener {
            onFavoriteIconClicked(uiProduct.product.id)
         }
         // Load our image
         productImageViewLoadingProgressBar.isVisible = true
         productImageView.load(data = uiProduct.product.image) {
            listener { request, result ->
               productImageViewLoadingProgressBar.isGone = true
            }
         }
      }
   }
}



