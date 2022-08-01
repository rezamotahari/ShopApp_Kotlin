package com.example.novinshop_project.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.example.novinshop_project.MainActivity
import com.example.novinshop_project.R

class Splash_Activty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_splash_activty)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },500)
    }
}