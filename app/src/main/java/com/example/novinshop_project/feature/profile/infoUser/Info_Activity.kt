package com.example.novinshop_project.feature.profile.infoUser

import android.os.Bundle
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.utils.PriceConverter
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Info_Activity : BaseActivity() {
    val infoViewModel :InfoViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        img_Back.setOnClickListener { finish() }
        txt_title_activity.text = getString(R.string.info)
        infoViewModel.infoLiveData.observe(this)
        {
            txt_name.text = it.nameFamily
            txt_phone.text = it.phone
            txt_email.text = ""
            txt_date.text = it.date
            txt_proccessing.text = it.proccesing.toString()
            txt_wallet.text = PriceConverter.priceConverter(it.wallet.toString())
        }
        infoViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
    }
}