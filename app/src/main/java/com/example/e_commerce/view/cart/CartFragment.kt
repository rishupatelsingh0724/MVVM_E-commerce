package com.example.e_commerce.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentCartBinding
import com.example.e_commerce.viewmodel.CartViewModel
import com.example.e_commerce.viewmodel.ProfileViewModel

class CartFragment : Fragment() {

    lateinit var banding: FragmentCartBinding
    lateinit var viewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        banding = FragmentCartBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class]

        viewModel.cartItemsData.observe(viewLifecycleOwner){
        }

















        return banding.root
    }


}