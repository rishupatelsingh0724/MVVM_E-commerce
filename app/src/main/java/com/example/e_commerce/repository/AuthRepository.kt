package com.example.e_commerce.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firebaseDB = FirebaseDatabase.getInstance()


    fun loginAuth(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onResult(true, "Login Success") }
            .addOnFailureListener { onResult(false, it.message.toString()) }
    }
    fun registerAuth(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { onResult(true, "Register Success") }
            .addOnFailureListener { onResult(false, it.message.toString()) }
    }

    fun insertUserAuthData(name: String, email: String,onResult: (Boolean, String) -> Unit) {
        val authData = HashMap<String, Any>()
        authData["name"] = name
        authData["email"] = email
        authData["userId"] = auth.currentUser!!.uid
        authData["image"] = "https://firebasestorage.googleapis.com/v0/b/firestore-2d951.appspot.com/o/MVVMProfileImage%2Fprofile.png?alt=media&token=c3eba9f3-3662-476e-8634-50afc43c2d22"
        firebaseDB.reference.child("UserAuthData").child(auth.currentUser!!.uid)
            .setValue(authData)
            .addOnSuccessListener {
                onResult(true, "Data Insert Success")
            }
            .addOnFailureListener {
                onResult(false, it.message.toString())
            }
    }
}