package com.example.e_commerce.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.MainActivity
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ActivityLoginBinding
import com.example.e_commerce.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]


        val loginBtn = binding.loginBtn
        val intentSigninPage = binding.intentSigninPage
        loginBtn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            viewModel.loginAuth(email, password) { isSuccess, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                if (isSuccess) startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        intentSigninPage.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }
}