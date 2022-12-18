package com.example.fakedemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.fakedemo.databinding.FragmentProductsListBinding
import com.example.fakedemo.model.ProductsListFragmentUiState
import com.example.fakedemo.model.uiFilter.UiFilter
import com.example.fakedemo.model.uiProduct.UiProduct
import com.example.fakedemo.ui.epoxyController.UiProductEpoxyController
import com.example.fakedemo.ui.viewmodel.ProductsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class ProductsListFragment : Fragment() {

   private var mBinding: FragmentProductsListBinding? = null
   private val binding by lazy { mBinding!! }

   private val viewModel: ProductsListViewModel by viewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      mBinding = FragmentProductsListBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      val controller = UiProductEpoxyController(viewModel)
      binding.epoxyRecyclerView.setController(controller = controller)
     // controller.setData(emptyList())

      combine(
         viewModel.store.stateFlow.map { it.products },
         viewModel.store.stateFlow.map { it.favoriteProductIds },
         viewModel.store.stateFlow.map { it.expandedProductIds },
         viewModel.store.stateFlow.map { it.productFilterInfo },
      ) { listOfProducts, setOfFavoriteIds, setOfExpandedProductIds, productFilterInfo ->
        val uiProducts =  listOfProducts.map { product ->
            UiProduct(
               product = product,
               isFavorite = setOfFavoriteIds.contains(product.id),
               isExpanded = setOfExpandedProductIds.contains(product.id)
            )
         }

         val uiFilters = productFilterInfo.filters.map { filter ->
            UiFilter(
               filter = filter,
               isSelected = productFilterInfo.selectedFilter?.equals(filter) == true
            )
         }.toSet()

        return@combine if(productFilterInfo.selectedFilter == null) {
            ProductsListFragmentUiState(filters = uiFilters, products = uiProducts)
         }else{
            ProductsListFragmentUiState(filters = uiFilters, products = uiProducts.filter {
               it.product.category == productFilterInfo.selectedFilter.value
            })
        }

      }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { uiState ->
         controller.setData(uiState)
      }
      viewModel.refreshProducts()

   }

   override fun onDestroyView() {
      super.onDestroyView()
      mBinding = null
   }


}