package com.example.novinshop_project.feature.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.novinshop_project.R
import com.example.novinshop_project.utils.NO
import com.example.novinshop_project.utils.YES
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_remove.*

class RemoveItemDialog:BottomSheetDialogFragment() {

var onDialogRemove:OnDialogRemove?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return  inflater.inflate(R.layout.dialog_remove,container,false)
    }

    fun setOnRemoveItem(onDialogRemove:OnDialogRemove)
    {
        this.onDialogRemove= onDialogRemove
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        btn_yes.setOnClickListener {

            onDialogRemove!!.onRemoveItemClick(YES)
        }

        btn_no.setOnClickListener {

            onDialogRemove!!.onRemoveItemClick(NO)
        }
    }
    interface  OnDialogRemove
    {
        fun onRemoveItemClick(type:String)
    }
}