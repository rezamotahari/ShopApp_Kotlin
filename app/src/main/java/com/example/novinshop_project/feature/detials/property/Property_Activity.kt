package com.example.novinshop_project.feature.detials.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Property_Activity : BaseActivity() {


    val propertyViewModel :PropertyViewModel by viewModel{ parametersOf(intent.getIntExtra("id",0))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        txt_title_activity.text = getString(R.string.property)
        img_Back.setOnClickListener {
            finish()
        }
        propertyViewModel.propertyLiveData.observe(this)
        {
            val adapterProperty :AdapterProperty by inject { parametersOf( it) }
            rc_product_property.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            rc_product_property.adapter =adapterProperty
            Timber.i("$it")
        }
        propertyViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
    }
}