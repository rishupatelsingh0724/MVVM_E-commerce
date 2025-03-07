package com.example.e_commerce.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.e_commerce.databinding.ActivityUserItemsDetailsBinding
import com.example.e_commerce.viewmodel.CartViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID

class UserItemsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserItemsDetailsBinding
    private lateinit var viewModel: CartViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserItemsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        val itemName = intent.getStringExtra("itemName")
        val itemPrice = intent.getStringExtra("itemPrice")
        val image = intent.getStringExtra("image")
        val itemDescription = intent.getStringExtra("itemDescription")

        binding.itemName.text = itemName
        binding.itemPrice.text = "$ $itemPrice"
        binding.itemDescription.text = itemDescription?.trim()
        Glide.with(this).load(image).into(binding.itemImage)

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnAddToCard.setOnClickListener {
            val currentUserID = FirebaseAuth.getInstance().currentUser?.uid.toString()
            val id = UUID.randomUUID().toString()
            viewModel.addToCart(
                this,
                currentUserID,
                id,
                itemName.toString(),
                itemPrice.toString(),
                image.toString(),
                itemDescription.toString()
            )
        }
    }
}