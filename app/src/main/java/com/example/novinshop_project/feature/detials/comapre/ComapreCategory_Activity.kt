package com.example.novinshop_project.feature.detials.comapre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.utils.PRODUCT_ONE
import com.example.novinshop_project.utils.PRODUCT_TWO
import kotlinx.android.synthetic.main.activity_comapre_category.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ComapreCategory_Activity : BaseActivity(),AdapterCategoryCompare.OnClickCategoryItem {

    val categoryCoampreViewModel: CategoryCoampreViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                "id",
                0
            )
        )
    }

    var idOne :Int ?=null
    var idTwo :Int ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comapre_category)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        idOne= intent.getIntExtra("id", 0)

        img_Back.setOnClickListener {
            finish()
        }
        categoryCoampreViewModel.categoryComapreLiveData.observe(this)
        {
            Timber.i("$it")
            val adapterCategoryCompare: AdapterCategoryCompare by inject { parametersOf(it) }
            rc_category_compare.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rc_category_compare.adapter = adapterCategoryCompare
            adapterCategoryCompare.setItemClickCategory(this)

        }

        categoryCoampreViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }

    }

    override fun onCLickCategory(productIdTwo: Int) {
       idTwo = productIdTwo
        startActivity(Intent(this,CompareProduct_Activity::class.java).apply {
            putExtra(PRODUCT_ONE,idOne)
            putExtra(PRODUCT_TWO,idTwo)
        })
    }
}