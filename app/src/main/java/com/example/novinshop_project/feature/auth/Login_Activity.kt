package com.example.novinshop_project.feature.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.novinshop_project.R
import com.example.novinshop_project.feature.auth.register.Register_Activity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Login_Activity : AppCompatActivity() {
    val authViewMolde :AuthViewMolde by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {

            val phone = edt_login.text.toString().trim()

            authViewMolde.login(phone)
        }

        authViewMolde.loginLiveData.observe(this)
        {
            Snackbar.make(coordinator,it.message,Snackbar.LENGTH_SHORT).show()
            finish()
        }

        txt_inten_register.setOnClickListener {
            startActivity(Intent(this,Register_Activity::class.java))
            finish()

        }
    }
}