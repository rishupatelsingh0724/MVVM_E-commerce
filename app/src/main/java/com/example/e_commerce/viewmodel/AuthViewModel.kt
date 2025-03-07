package com.example.e_commerce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.model.authUserModel.UserAuthModel
import com.example.e_commerce.repository.AuthRepository

class AuthViewModel() : ViewModel() {
    private val authRepository = AuthRepository()


    fun loginAuth(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        authRepository.loginAuth(email, password, onResult)
    }
    fun registerAuth(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        authRepository.registerAuth(email, password, onResult)
    }

    fun insertUserAuthData(name: String, email: String,onResult: (Boolean, String) -> Unit) {
        authRepository.insertUserAuthData(name,email,onResult)
    }


}