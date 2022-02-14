package com.example.novinshop_project.feature.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.novinshop_project.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAddress_Activity : AppCompatActivity() {
    val addressViewModel: AddressViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        txt_title_activity.text = getString(R.string.addd_address)
        img_Back.setOnClickListener {
            finish()
        }
        btn_add_address.setOnClickListener {


            if (edt_adress.length() > 0 || edt_city.length() > 0 || edt_code.length() > 0) {
                val jsonObject = JsonObject()
                jsonObject.addProperty("number", edt_pelak.text.toString().trim())
                jsonObject.addProperty("unit", edt_unit.text.toString().trim())
                jsonObject.addProperty("address", edt_adress.text.toString().trim())
                jsonObject.addProperty("poatal_code", edt_code.text.toString())
                jsonObject.addProperty("name_family", edt_family.text.toString())
                jsonObject.addProperty("phone", edt_phone.text.toString().trim())
                jsonObject.addProperty("lat", "1321321321")
                jsonObject.addProperty("lang", "32132132131")
                jsonObject.addProperty("State", edt_ostan.text.toString().trim())
                jsonObject.addProperty("city", edt_city.text.toString().trim())
                addressViewModel.addAddress(jsonObject)
            } else {
                Snackbar.make(coordinators, "لطفا همه اطلاعات را وارد کنید", Snackbar.LENGTH_SHORT)
                    .show()
            }


        }
        addressViewModel.addAddressLiveData.observe(this)
        {
            Toast.makeText(this, "اطلاعات شما ثبت شد", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}