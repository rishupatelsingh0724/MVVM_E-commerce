package com.example.e_commerce.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.model.homeDataModel.HomeItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {
    val liveData = MutableLiveData<List<HomeItemModel>>()

    private val database = FirebaseDatabase.getInstance().reference

    fun fetchHomeItems(context: Context) {
        database.child("UserItemData")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val itemList = mutableListOf<HomeItemModel>()
                    for (itemSnapshot in snapshot.children) {
                        val item = itemSnapshot.getValue(HomeItemModel::class.java)
                        item?.let { itemList.add(it) }
                    }
                    liveData.value = itemList
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Error :${error.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }
}