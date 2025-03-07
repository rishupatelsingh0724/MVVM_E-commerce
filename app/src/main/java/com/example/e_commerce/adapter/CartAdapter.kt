package com.example.e_commerce.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.model.CartProductDataModel

class CartAdapter(private val context: Context, private val cartList: ArrayList<CartProductDataModel>):RecyclerView.Adapter<CartAdapter.CartProductViewHolder>() {
    class CartProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}