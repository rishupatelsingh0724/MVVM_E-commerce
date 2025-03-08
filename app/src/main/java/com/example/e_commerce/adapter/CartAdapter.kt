package com.example.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce.R
import com.example.e_commerce.model.CartProductDataModel
import com.google.firebase.database.FirebaseDatabase

class CartAdapter(private val context: Context, private val cartList: ArrayList<CartProductDataModel>):RecyclerView.Adapter<CartAdapter.CartProductViewHolder>() {
    class CartProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.cardAddItemImage)
        val name: TextView = itemView.findViewById(R.id.cardAddItemName)
        val price: TextView = itemView.findViewById(R.id.cardIAddtemPrice)
        val remove: ImageView = itemView.findViewById(R.id.cardRemoveIcon)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_adapter_deasing,parent,false)
        return CartProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val cartItem = cartList[position]
        holder.name.text = cartItem.itemName
        holder.price.text = cartItem.itemPrice
        Glide.with(context).load(cartItem.image).into(holder.image)
        holder.remove.setOnClickListener {
            FirebaseDatabase.getInstance().getReference("AddToCartProducts").child(cartItem.itemId!!).removeValue()
            cartList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartList.size)
        }
    }
}