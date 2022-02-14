package com.example.novinshop_project.feature.detials.comapre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.feature.detials.comapre.adapter.AdapterCompareParent
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.PriceConverter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_compare_product.*
import kotlinx.android.synthetic.main.layout_by_product.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class CompareProduct_Activity : AppCompatActivity() {

    val compareProductViewModel :CompareProductViewModel by viewModel { parametersOf(intent.extras!!)}
val imageLoadingServices :ImageLoadingServices by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_product)

        txt_title_activity.text = getString(R.string.compare)
        img_Back.setOnClickListener {

            finish()
        }
       compareProductViewModel.compareProductLiveData.observe(this)
       {
           txt_price_product_one.text = PriceConverter.priceConverter(it.price1!!)
           txt_price_product_two.text = PriceConverter.priceConverter(it.price2!!)
           txt_name_peroduct_one.text = it.name1
           txt_name_peroduct_two.text = it.name2
           imageLoadingServices.loadImage(img_pr_one,it.imageurl1)
           imageLoadingServices.loadImage(img_pr_two,it.imageurl2)
           val adapterCompareParent :AdapterCompareParent by inject { parametersOf(it.property) }
           rc_comapre_property.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
           rc_comapre_property.adapter = adapterCompareParent

           Timber.i("$it")

       }

    }
}