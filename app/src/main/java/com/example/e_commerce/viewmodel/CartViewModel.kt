package com.example.e_commerce.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.model.CartProductDataModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.razorpay.Checkout
import org.json.JSONObject
import java.util.UUID

class CartViewModel:ViewModel() {

    private val _cartItemsData = MutableLiveData<ArrayList<CartProductDataModel>?>()
    val cartItemsData: LiveData<ArrayList<CartProductDataModel>?> get() = _cartItemsData
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
        val currentUserID = FirebaseAuth.getInstance().currentUser?.uid
        val cartItemsRef = db.getReference("AddToCartProducts").child(currentUserID!!)
        cartItemsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val list=ArrayList<CartProductDataModel>()

                for (cartItemSnapshot in snapshot.children) {
                    val cartItem = cartItemSnapshot.getValue(CartProductDataModel::class.java)
                    if (cartItem != null) {
                        list.add(cartItem)
                    }
                    _cartItemsData.postValue(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Something went wrong ${error.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun insertDataProceedToOrder(context: Context,itemName: String, itemPrice: String, image: String,amount:String){
        val currentUserID = FirebaseAuth.getInstance().currentUser?.uid
        val id = UUID.randomUUID().toString()

        val data = mapOf(
            "itemId" to currentUserID,
            "id" to id,
            "itemName" to itemName,
            "itemPrice" to itemPrice,
            "image" to image
        )


        db.getReference("ProceedToOrder").child(currentUserID!!).child(id).setValue(data)
            .addOnSuccessListener {
                Toast.makeText(context, "Proceed To Order", Toast.LENGTH_SHORT).show()
                startPayment(context,amount)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Something went wrong ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun startPayment(context: Context,amount :String) {
        val activity = context
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_7QRDUFvP75uLJS") // Razorpay Key ID yahan paste karo

        try {
            val options = JSONObject()
            options.put("name", "Your App Name")
            options.put("description", "Test Payment")
            options.put("currency", "INR")
            options.put("amount", amount) // ₹500.00 (50000 paise)

            val prefill = JSONObject()
            prefill.put("email", "customer@example.com")
            prefill.put("contact", "9876543210")

            options.put("prefill", prefill)

            checkout.open(activity as Activity?, options)

        } catch (e: Exception) {
            Toast.makeText(activity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


}