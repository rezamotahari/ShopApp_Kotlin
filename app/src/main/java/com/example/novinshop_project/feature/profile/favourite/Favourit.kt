package com.example.novinshop_project.feature.profile.favourite

import android.content.Intent
import android.os.Bundle
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
import com.example.novinshop_project.feature.detials.Details_Product
import com.example.novinshop_project.utils.PRODUCT_ID
import kotlinx.android.synthetic.main.fragment_favourit.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class Favourit :BaseFragment() ,AdapterFavourite.OnCLickItemFavourite{

val favouriteViewModel :FavouriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      //  favouriteViewModel.getFavourite()

        favouriteViewModel.favouriteLiveData.observe(viewLifecycleOwner)
        {
            Timber.i("$it")
            rc_favourit.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
            val adapterFavourite:AdapterFavourite by inject { parametersOf(it) }
            rc_favourit.adapter= adapterFavourite
            adapterFavourite.setOnClickItemFavourite(this)
        }
        favouriteViewModel.progressBarLiveData.observe(viewLifecycleOwner)
        {
            setProgressBar(it)
        }
        favouriteViewModel.emptyStateLiveData.observe(viewLifecycleOwner)
        {
            val parent = view.findViewById<LinearLayout>(R.id.lnr_empty_favourite)
            if (it.show)
            {
                val emptyState = showEmptyState(R.layout.layout_empty_state_favourite)
                emptyState?.let { view->

                    val txtEmpty= view.findViewById<TextView>(R.id.txt_state_favourite)
                    txtEmpty.text= getString(it.message)

                }

            }
            else
            {
                parent?.let {

                    it.visibility =View.GONE
                }
            }
        }
    }

    override fun onClickFavourite(productId: Int) {
        startActivity(Intent(context,Details_Product::class.java).apply {
            putExtra(PRODUCT_ID,productId)
        })
    }

    override fun onStart() {
        super.onStart()
        favouriteViewModel.getFavourite()
    }
}