package com.aerotech.flytix

import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.aerotech.flytix.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomnav)
//        //navcontroler,nacdestination,bundle
//        navController.addOnDestinationChangedListener { navcontroler, destination, bundle ->
//
//            when (destination.id) {
//                R.id.detail -> {
//                    bottomNavView.visibility = View.GONE
//                }
//                else -> {
//                    bottomNavView.visibility = View.VISIBLE
//                }
//            }
//        }
//        NavigationUI.setupWithNavController(bottomNavView, navController)
//    }
//
//    fun clearBackStackInclusive(tag: String?) {
//        supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//    }
}