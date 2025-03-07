package com.example.e_commerce.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.model.CartProductDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartViewModel:ViewModel() {

    private val _cartItemsData = MutableLiveData<CartProductDataModel?>()
    val cartItemsData: LiveData<CartProductDataModel?> get() = _cartItemsData
    private val db = FirebaseDatabase.getInstance()

    fun addToCart(
        @SuppressLint("RestrictedApi") context: Context,
        currentUserID: String,
        id: String,
        itemName: String,
        itemPrice: String,
        image: String,
        itemDescription: String
    ) {
        val data = mapOf(
            "itemId" to currentUserID,
            "id" to id,
            "itemName" to itemName,
            "itemPrice" to itemPrice,
            "image" to image,
            "itemDescription" to itemDescription
        )
        db.getReference("AddToCartProducts").child(currentUserID).child(id).setValue(data)
            .addOnSuccessListener {
                Toast.makeText(context, "$itemName add to card successfully", Toast.LENGTH_SHORT)
                    .show()

            }
            .addOnFailureListener {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }


    fun fetchCartItems(context: Context) {
        val cartItemsRef = db.getReference("AddToCartProducts")
        cartItemsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (cartItemSnapshot in snapshot.children) {
                    val cartItem = cartItemSnapshot.getValue(CartProductDataModel::class.java)
                    if (cartItem != null) {
                        _cartItemsData.postValue(cartItem)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Something went wrong ${error.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

}