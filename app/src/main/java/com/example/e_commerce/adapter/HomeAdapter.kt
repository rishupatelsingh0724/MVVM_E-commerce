package com.example.e_commerce.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.e_commerce.R
import com.example.e_commerce.model.homeDataModel.HomeItemModel
import com.example.e_commerce.view.home.UserItemsDetailsActivity

class HomeAdapter(val context: Context, private val itemList: ArrayList<HomeItemModel>):RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>() {
    class HomeItemViewHolder(itemView: View):ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.homeIvFoodImage)
        val foodName: TextView = itemView.findViewById(R.id.homeTvFoodName)
        val foodPrice: TextView = itemView.findViewById(R.id.homeTvFoodNamePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_deasing_home_frag,parent,false)
        return HomeItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.foodName.text = item.itemName
        holder.foodPrice.text = item.itemPrice
        Glide.with(context).load(item.image).into(holder.foodImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UserItemsDetailsActivity::class.java)
            intent.putExtra("itemId", item.itemId)
            intent.putExtra("itemName", item.itemName)
            intent.putExtra("itemPrice", item.itemPrice)
            intent.putExtra("image", item.image)
            context.startActivity(intent)
        }
    }
}