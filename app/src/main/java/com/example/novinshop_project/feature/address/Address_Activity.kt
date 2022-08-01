package com.example.novinshop_project.feature.address

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.data.ResponseAddress
import com.example.novinshop_project.utils.ADDRESS
import com.example.novinshop_project.utils.ADDRESS_ID
import com.example.novinshop_project.utils.FAMILY
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Address_Activity :BaseActivity(),AdapterShowAddress.OnClickItemAddress {
    val addressViewModel :AddressViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_address)
        txt_title_activity.text = getString(R.string.address)
        img_Back.setOnClickListener { finish() }
        fab_address.setOnClickListener {
            startActivity(Intent(this,AddAddress_Activity::class.java))
        }
        addressViewModel.addressRepositoryLiveData.observe(this)
        {

            rc_show_address.layoutManager  = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            val adapterShowAddress :AdapterShowAddress by inject { parametersOf(it) }
            rc_show_address.adapter = adapterShowAddress
            Timber.i("$it")
            adapterShowAddress.setOnCLickAddress(this)
        }

        addressViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
    }

    override fun onClickAddress(address: ResponseAddress) {

        setResult(1001, Intent()
            .putExtra(FAMILY,address.nameFamily)
            .putExtra(ADDRESS,address.address)
            .putExtra(ADDRESS_ID,address.id))
        finish()

    }

    override fun onStart() {
        super.onStart()
        addressViewModel.getAddress()
    }
}