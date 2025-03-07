package com.example.e_commerce.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.databinding.ActivityRegisterBinding
import com.example.e_commerce.viewmodel.AuthViewModel

@Suppress("NAME_SHADOWING")
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setContentView(binding.root)

        val registerBtn = binding.signINBtn
        val intentLoginPage = binding.intentLoginPage

        registerBtn.setOnClickListener {
            val name = binding.nameEdt.text.toString()
            val email = binding.edtSignupEmail.text.toString()
            val password = binding.edtSignupPassword.text.toString()

            viewModel.registerAuth(email, password) { isSuccess, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                if (isSuccess) {
                    viewModel.insertUserAuthData(name, email) { isSuccess, message ->
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
        intentLoginPage.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}