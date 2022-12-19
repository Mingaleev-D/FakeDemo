package com.example.fakedemo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fakedemo.R
import com.example.fakedemo.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

   private var mBinding: FragmentCartBinding? = null
   private val binding by lazy { mBinding!! }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      mBinding = FragmentCartBinding.bind(view)

   }


   override fun onDestroyView() {
      super.onDestroyView()
      mBinding = null
   }



}