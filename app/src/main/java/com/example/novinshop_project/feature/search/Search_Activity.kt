package com.example.novinshop_project.feature.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.feature.detials.Details_Product
import com.example.novinshop_project.feature.search.adapter.AdapterPartOne
import com.example.novinshop_project.feature.search.adapter.AdapterPartTwo
import com.example.novinshop_project.feature.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Search_Activity : BaseActivity(), AdapterPartTwo.OnClickProductItem {
    val searchViewModel: SearchViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_search)

        txt_title_activity.text = getString(R.string.searchProduct)
        img_Back.setOnClickListener {
            finish()
        }
        img_search.setOnClickListener {
            if (edt_search.length() > 0) {
                searchViewModel.search(edt_search.text.toString().trim())
            }

        }
        searchViewModel.searchLiveData.observe(this) {
            edt_search.text.clear()
            Timber.i("$it")
            if (!it.part1.isNullOrEmpty())
                txt_cat_search.visibility = View.VISIBLE

            if (!it.part2.isNullOrEmpty()) {
                txt_product_search.visibility = View.VISIBLE
                lnr_search.visibility = View.VISIBLE
                lnr_empty_search.visibility = View.GONE
            } else {
                lnr_search.visibility = View.GONE
                lnr_empty_search.visibility = View.VISIBLE
            }
            rc_search_partTwo.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            rc_search_partOne.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val adapterPartTwo: AdapterPartTwo by inject { parametersOf(it.part2) }
            val adapterPartOne: AdapterPartOne by inject { parametersOf(it.part1) }
            rc_search_partOne.adapter = adapterPartOne
            rc_search_partTwo.adapter = adapterPartTwo
            adapterPartTwo.setOnCLickProductItem(this)
        }
        searchViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
    }

    override fun onClickProduct(productId: Int) {
        startActivity(Intent(this, Details_Product::class.java).apply {

            putExtra("id", productId)
        })
    }
}