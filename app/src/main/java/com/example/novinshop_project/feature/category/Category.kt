package com.example.novinshop_project.feature.category

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseFragment
import com.example.novinshop_project.feature.category.adapter.AdapterCategory
import com.example.novinshop_project.feature.category.subCat.SubCat_Activity
import com.example.novinshop_project.feature.category.viewmodel.CategoryViewModel
import com.example.novinshop_project.utils.CATEGORY_ID
import com.example.novinshop_project.utils.PRODUCT_ID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class Category :BaseFragment(),AdapterCategory.OnClickSubCatItem {
    val categoryViewModel : CategoryViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_category, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val parent =view.findViewById<LinearLayout>(R.id.lnr_category_container)
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner)
        {

            for(item in it)
            {
            val txtTitle =TextView(requireContext())
                val rcCateory= RecyclerView(requireContext())
                txtTitle.text = item.title
                val rightdingDp = 8
                val topPaddingDp = 8
                val bottomPaddingDp = 8
                val density = requireContext().resources.displayMetrics.density
                val rightPixel = (rightdingDp*density).toInt()
                val topPixel = (topPaddingDp*density).toInt()
                val bottomPixel = (bottomPaddingDp*density).toInt()
                txtTitle.setPadding(0,topPixel,rightPixel,bottomPixel)
                parent.addView(txtTitle)
                rcCateory.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                val adapterCategory :AdapterCategory by inject { parametersOf(item.subcat) }
                rcCateory.adapter = adapterCategory
                parent.addView(rcCateory)
                adapterCategory.setSubCatId(this)

            }
//            Timber.i("$it")
        }

        categoryViewModel.progressBarLiveData.observe(viewLifecycleOwner)
        {
            setProgressBar(it)
        }
    }

    override fun onClickSubCat(catId: Int) {
        startActivity(Intent(context,SubCat_Activity::class.java).apply {

            putExtra(CATEGORY_ID,catId)

        })
    }

}