package com.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.presentation.R
import com.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        binding.navMenu.setupWithNavController(navController)

        binding.navMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_tickets -> {
                    navController.navigate(R.id.navigation_tickets)
                    true
                }
                R.id.navigation_hotels -> {
                    navController.navigate(R.id.navigation_hotels)
                    true
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    true
                }
                R.id.navigation_other -> {
                    navController.navigate(R.id.navigation_other)
                    true
                }
                R.id.navigation_subscriptions -> {
                    navController.navigate(R.id.navigation_subscriptions)
                    true
                }
                else -> false
            }
        }
    }
}
