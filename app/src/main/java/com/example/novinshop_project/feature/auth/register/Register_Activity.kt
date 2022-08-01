package com.example.novinshop_project.feature.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.novinshop_project.R
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.feature.auth.verify.Verify_Activity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class Register_Activity : AppCompatActivity() {
    val authViewMolde :AuthViewMolde by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_register)

        btn_checkUser.setOnClickListener {

            if (edt_name_family.length() == 0 || edt_phone_checkUser.length() == 0)
            {
                Snackbar.make(coordinator,"مقادیر لازم را وارد کنید",Snackbar.LENGTH_SHORT).show()

            }
            else
            {
                val phone = edt_phone_checkUser.text.toString()
                authViewMolde.checkUser(phone)
            }


        }

        authViewMolde.regigisterCheckUserLiveData.observe(this){
            Timber.i("$it")
            if (it.message.equals("1")){

                startActivity(Intent(this,Verify_Activity::class.java)
                    .apply {
                        putExtra("name",edt_name_family.text.toString())
                        putExtra("phone",edt_phone_checkUser.text.toString())
                    })
                finish()
            }
            else
            {
                Snackbar.make(coordinator,it.message,Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}