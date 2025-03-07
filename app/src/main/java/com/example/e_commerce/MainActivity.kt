package com.example.e_commerce

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.e_commerce.view.cart.CartFragment
import com.example.e_commerce.view.home.HomeFragment
import com.example.e_commerce.view.ordel.OrderFragment
import com.example.e_commerce.view.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nevButton: BottomNavigationView = findViewById(R.id.bottomNavigation)
        loadFragment(HomeFragment())
        nevButton.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.cartFragment -> {
                    loadFragment(CartFragment())
                    true
                }

                R.id.orderFragment -> {
                    loadFragment(OrderFragment())
                    true
                }

                R.id.profileFragment -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}