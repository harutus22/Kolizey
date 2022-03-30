package com.example.kolizey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kolizey.fragment.util_fragments.IntroductionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.frag)
        bottom.setupWithNavController(navController)
        supportFragmentManager.beginTransaction().add(R.id.fragment, IntroductionFragment()).commit()
    }
}