package com.example.novinshop_project.feature.home.subCatLevel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.novinshop_project.R
import com.example.novinshop_project.utils.PRODUCT_ID
import kotlinx.android.synthetic.main.fragment_sub_cat_level__fragmnet.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SubCatLevel_Fragmnet : Fragment() {

val subcatLevelViewModel:SubcatLevelViewModel by viewModel { parametersOf(arguments?.getInt(
    PRODUCT_ID,0)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_cat_level__fragmnet, container, false)
    }

    companion object {

        fun newInstance(subCatId:Int) =
            SubCatLevel_Fragmnet().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ID,subCatId)

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subcatLevelViewModel.subCatLiveData.observe(viewLifecycleOwner){
            Timber.i("$it")

            val adapterSubcatLevel :AdapterSubcatLevel by inject { parametersOf(it) }
            rc_subcat_level.layoutManager = GridLayoutManager(context,3)
            rc_subcat_level.adapter = adapterSubcatLevel

        }
    }
}