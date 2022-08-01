package com.example.novinshop_project.feature.allAmazing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.feature.allAmazing.adapter.AdapterAllAmazing
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_all_amazing_actvity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllAmazing_Actvity : BaseActivity() {
    val sortViewModel :SortViewModel by viewModel { parametersOf(0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_all_amazing_actvity)
        img_Back.setOnClickListener { finish() }
        txt_title_activity.text = getString(R.string.all_amazing)
        sortViewModel.allAlmazingLiveData.observe(this)
        {
            val adapterAllAmazing :AdapterAllAmazing by inject { parametersOf(it) }
            rc_filter.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            rc_filter.adapter = adapterAllAmazing
        }
        sortViewModel.selectTitleLiveData.observe(this)
        {
            txt_filter.text = getString(it)
        }
        sortViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
        rltv_filter.setOnClickListener {

            val dialogFilter =MaterialAlertDialogBuilder(this)
                .setSingleChoiceItems(R.array.sortArray,sortViewModel.sort){
                    dialog,indexItem->
                    dialog.dismiss()
                    sortViewModel.selectedItemDialog(indexItem)
                }
                dialogFilter.show()
        }

    }
}