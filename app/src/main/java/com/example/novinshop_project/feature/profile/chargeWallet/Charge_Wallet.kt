package com.example.novinshop_project.feature.profile.chargeWallet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.novinshop_project.R
import com.example.novinshop_project.feature.auth.TokenContainer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_charge_wallet.*

class Charge_Wallet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charge_wallet)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        btn_wallet_charge.setOnClickListener {

            if (edt_wallet_charge.length()>0)
            {
                val intent = Intent(Intent.ACTION_VIEW,Uri.parse
                    ("http://digishop.novindevelopers.ir/api/v1/profile/wallet_charge.php?wallet_charge="+edt_wallet_charge.text.toString().trim()+"&token="+TokenContainer.token))
                startActivity(intent)
                finish()
            }
            else
            {
                edt_wallet_charge.error="لطفا مبلغ را وارد کنید"
                Snackbar.make(coordinatorw,"لطفا مبلغ را وارد کنید",Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}