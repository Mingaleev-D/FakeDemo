package com.example.fakedemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fakedemo.R
import com.example.fakedemo.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {
   private var mBinding: FragmentProfileBinding? = null
   private val binding by lazy { mBinding!! }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      mBinding = FragmentProfileBinding.bind(view)

   }


   override fun onDestroyView() {
      super.onDestroyView()
      mBinding = null
   }



}