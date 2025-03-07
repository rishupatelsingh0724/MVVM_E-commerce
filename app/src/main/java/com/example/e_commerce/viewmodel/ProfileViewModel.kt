package com.example.e_commerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.model.profile.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileViewModel : ViewModel() {

    private val _profileData = MutableLiveData<UserProfile?>()
    val profileData: LiveData<UserProfile?> get() = _profileData

    private val auth = FirebaseAuth.getInstance()
    private val firebaseDB = FirebaseDatabase.getInstance()
    fun fetchProfileData() {
        val resv = firebaseDB.reference.child("UserAuthData").child(auth.currentUser!!.uid)
        resv.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(UserProfile::class.java)
                if (data != null) {
                    _profileData.postValue(data)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}