package com.example.novinshop_project.feature.category.subCat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.feature.category.adapter.AdapterBrands
import com.example.novinshop_project.feature.category.adapter.AdapterPopularCat
import com.example.novinshop_project.feature.category.adapter.AdapterSubCat
import com.example.novinshop_project.feature.category.brandProduct.BrandProduct_Activity
import com.example.novinshop_project.feature.category.subCat.viewmodel.SubCatViewModel
import com.example.novinshop_project.utils.BRAND_ID
import com.example.novinshop_project.utils.CATEGORY_ID
import kotlinx.android.synthetic.main.activity_sub_cat.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SubCat_Activity : BaseActivity(),AdapterBrands.OnClickBrandId {
    val subCatViewModel :SubCatViewModel by viewModel { parametersOf(intent.getIntExtra(CATEGORY_ID,0))  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_cat)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        subCatViewModel.subCatLiveData.observe(this)
        {
            Timber.i("$it")
            rc_subCats.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            val adapterSubCat :AdapterSubCat by inject { parametersOf(it) }
            rc_subCats.adapter = adapterSubCat
        }

        subCatViewModel.brandsLiveData.observe(this)
        {
            Timber.i("$it")
            rc_brands_category.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            val adapterBrands :AdapterBrands by inject { parametersOf(it) }
            rc_brands_category.adapter = adapterBrands
            val itemDecorationhORIZONTAL: RecyclerView.ItemDecoration =
                DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
            rc_brands_category.addItemDecoration(itemDecorationhORIZONTAL)
            adapterBrands.setOnClickBrand(this)
        }

        subCatViewModel.popularCatLiveData.observe(this)
        {
            Timber.i("$it")
            rc_favourite_Product.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            val adapterPopularCat :AdapterPopularCat by inject { parametersOf(it) }
            rc_favourite_Product.adapter = adapterPopularCat
            val itemDecorationhORIZONTAL: RecyclerView.ItemDecoration =
                DividerItemDecoration(this
                    , DividerItemDecoration.HORIZONTAL)
            rc_favourite_Product.addItemDecoration(itemDecorationhORIZONTAL)

        }

        subCatViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }


    }

    override fun onClickBrand(brandId: Int) {
        startActivity(Intent(this,BrandProduct_Activity::class.java).apply {

            putExtra(BRAND_ID,brandId)
        })
    }
}