package com.example.fakedemo.ui.epoxyController

import androidx.core.content.ContextCompat
import com.example.fakedemo.R
import com.example.fakedemo.databinding.EpoxyModelProductFilterBinding
import com.example.fakedemo.model.domain.Filter
import com.example.fakedemo.model.uiFilter.UiFilter
import com.example.fakedemo.ui.epoxy.ViewBindingKotlinModel

/**
 * @author : Mingaleev D
 * @data : 17/12/2022
 */

data class UiProductFilterEpoxyModel(
   val uiFilter:UiFilter,
   val onFilterSelected:(Filter) -> Unit
) : ViewBindingKotlinModel<EpoxyModelProductFilterBinding>(R.layout.epoxy_model_product_filter){

   override fun EpoxyModelProductFilterBinding.bind() {
      root.setOnClickListener { onFilterSelected(uiFilter.filter) }
      filterNameTextView.text = uiFilter.filter.displayText

      val cardBackgroundColorResId = if (uiFilter.isSelected){
         R.color.purple_500
      }else{
         R.color.purple_200
      }
      root.setCardBackgroundColor(ContextCompat.getColor(root.context,cardBackgroundColorResId))
   }

}