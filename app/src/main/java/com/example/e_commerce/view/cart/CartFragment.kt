package com.example.e_commerce.view.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.adapter.CartAdapter
import com.example.e_commerce.databinding.FragmentCartBinding
import com.example.e_commerce.model.CartProductDataModel
import com.example.e_commerce.viewmodel.CartViewModel
import com.razorpay.PaymentResultListener

class CartFragment : Fragment(), PaymentResultListener {

    private lateinit var banding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    private lateinit var cartItemList: ArrayList<CartProductDataModel>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        banding = FragmentCartBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class]

        cartItemList = ArrayList()
        cartAdapter = CartAdapter(requireContext(), cartItemList)
        cartRecyclerView = banding.recyclerView
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.adapter = cartAdapter
        cartRecyclerView.setHasFixedSize(true)
        cartAdapter.notifyDataSetChanged()

        viewModel.fetchCartItems(requireContext())

        viewModel.cartItemsData.observe(viewLifecycleOwner){
            cartItemList.clear()
            cartItemList.addAll(it!!)
            cartAdapter.notifyDataSetChanged()
        }


        val payBtn = banding.proceedToPey
        payBtn.setOnClickListener {

        }














        return banding.root
    }

    override fun onPaymentSuccess(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }


}