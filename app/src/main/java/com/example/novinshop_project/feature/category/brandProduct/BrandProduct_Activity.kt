package com.example.novinshop_project.feature.category.brandProduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.feature.category.brandProduct.adapter.AdapterBrandProduct
import com.example.novinshop_project.feature.category.brandProduct.viewmodel.BrandBannerViewModel
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.BRAND_ID
import kotlinx.android.synthetic.main.activity_brand_product.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class BrandProduct_Activity : BaseActivity() {

    val bannerViewModel: BrandBannerViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                BRAND_ID, 0
            )
        )
    }

    val imageLoadingServices: ImageLoadingServices by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_brand_product)
        txt_title_activity.text = getString(R.string.brandsProduct)
        img_Back.setOnClickListener { finish() }
        bannerViewModel.bannerBrandLiveData.observe(this)
        {
            imageLoadingServices.loadImage(imgBanner_brand, it.banner)
            imageLoadingServices.loadImage(imgIcon, it.icon)
        }

        bannerViewModel.brandProductLiveData.observe(this)
        {
            val adapterBrandProduct: AdapterBrandProduct by inject { parametersOf(it) }
            rc_brand_product.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val itemDecorationVerticalOrientation: RecyclerView.ItemDecoration =
                DividerItemDecoration(
                    this, DividerItemDecoration.VERTICAL
                )
            rc_brand_product.adapter = adapterBrandProduct
            rc_brand_product.addItemDecoration(itemDecorationVerticalOrientation)
            Timber.i("$it")
        }
        bannerViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
    }
}