package com.example.novinshop_project.feature.nextlevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.novinshop_project.R
import com.example.novinshop_project.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_buy.*
import kotlinx.android.synthetic.main.dialog_remove.*

class BuyDialog:BottomSheetDialogFragment() {

var onDialogBuy:OnDialogBuy?=null
var wallet:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wallet = arguments?.getString(WALLET)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.dialog_buy,container,false)
    }

    fun setOnBuyDialog(onDialogBuy:OnDialogBuy)
    {
        this.onDialogBuy= onDialogBuy
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_price.text = PriceConverter.priceConverter(wallet.toString()) +" مبلغ کیف پول شما  "

        txt_direct.setOnClickListener {

            onDialogBuy!!.onDialogBuy(DIRECT)
        }

        txt_wallet.setOnClickListener {

            rltv_buy.visibility =View.VISIBLE

        }
        txt_buy_wallet.setOnClickListener {
            onDialogBuy!!.onDialogBuy(WALLET)
        }
    }
        companion object
        {
            fun newInstance(wallet:String) =
                BuyDialog().apply {
                    arguments = Bundle().apply {
                        putString(WALLET,wallet)
                    }

                }
        }
    interface  OnDialogBuy
    {
        fun onDialogBuy(type:String)
    }
}