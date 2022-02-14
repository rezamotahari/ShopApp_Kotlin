package com.example.novinshop_project.feature.detials.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.novinshop_project.R
import com.example.novinshop_project.utils.CHART
import com.example.novinshop_project.utils.COMPARE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_more.*

class MoreDialogBottomSheet:BottomSheetDialogFragment() {

    lateinit var onClickMoreDialog: OnClickMoreDialog

    var myView:View ?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (myView == null)
        {
            myView = inflater.inflate(R.layout.dialog_more,null)
        }
        return  myView

    }

    fun setOnclickMoreDialog(onClickMoreDialog: OnClickMoreDialog)
    {
        this.onClickMoreDialog = onClickMoreDialog
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rltv_chart.setOnClickListener {

         onClickMoreDialog.onclickMore(CHART)
        }
        rltv_compare.setOnClickListener {

            onClickMoreDialog.onclickMore(COMPARE)
        }
    }


    interface OnClickMoreDialog{
        fun  onclickMore(type:String)
    }
}