package com.example.novinshop_project.feature.nextlevel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.browser.customtabs.CustomTabsIntent

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.feature.address.Address_Activity
import com.example.novinshop_project.feature.auth.TokenContainer
import com.example.novinshop_project.utils.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_next_level.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import kotlin.math.log

class NextLevel_Activity : BaseActivity() ,BuyDialog.OnDialogBuy{
    val checkOutListViewModel: CheckOutListViewModel by viewModel()
    var dialogBuy :BuyDialog ?= null
    var wallet : String?=null
    var shippingPrice : String?=null
    var payBale : Int?=null
    var reciveId :Int?=null
    private  val TAG = "NextLevel_Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_next_level)
        txt_title_activity.text = getString(R.string.info_deleviry)
        img_Back.setOnClickListener {
            finish()
        }
        txt_intent_address.setOnClickListener {

            // startActivity(Intent(this,Address_Activity::class.java))
            val intent = Intent(this, Address_Activity::class.java)
            resultLuncher.launch(intent)

        }
        btn_buy_product.setOnClickListener {

            dialogBuy = BuyDialog.newInstance(wallet!!)
            dialogBuy!!.show(supportFragmentManager,null)
            dialogBuy!!.setOnBuyDialog(this)
        }

        checkOutListViewModel.checkOutListLiveData.observe(this)
        {
            reciveId = it.addressId
            wallet = it.wallet
            payBale = it.payablePrice
            shippingPrice = it.shippingCost.toString()
            Timber.i("$it")
            txt_delivry.text = it.deliveryTime
            txt_price.text = PriceConverter.priceConverter(it.totalPrice.toString())
            txt_offprice.text = PriceConverter.priceConverter(it.totalOffPrice.toString())
            txt_shipping.text = PriceConverter.priceConverter(it.shippingCost.toString())
            txt_payblale.text = PriceConverter.priceConverter(it.payablePrice.toString())
            txt_price_product.text = PriceConverter.priceConverter(it.payablePrice.toString())
            txt_address.text = it.address
            txt_name.text = it.nameFamily
            rc_checkout_product.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val adapterCheckOutProduct: AdapterCheckOutProduct by inject { parametersOf(it.productItemDeliveries) }
            rc_checkout_product.adapter = adapterCheckOutProduct
        }

        checkOutListViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }
    }

    var resultLuncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode == 1001) {
                val dataResulat :Intent? = it.data
            txt_name.text = dataResulat?.getStringExtra(FAMILY)
            txt_address.text = dataResulat?.getStringExtra(ADDRESS)
            reciveId = dataResulat?.getIntExtra(ADDRESS_ID,0)
        }
    }

    override fun onDialogBuy(type: String) {
       when(type)
       {
           WALLET->
           {
               if (wallet!!.toInt()> payBale!! )
               {

                   checkOutListViewModel.insertTrancation(reciveId.toString(),"#",shippingPrice!!,payBale.toString())

                checkOutListViewModel.trancationLiveData.observe(this)
                {
                    Snackbar.make(coordinatorl,it.message,Snackbar.LENGTH_SHORT).show()
                    dialogBuy!!.dismiss()
                    finish()
                    val countItem = EventBus.getDefault().getStickyEvent(ResponseCount::class.java)
                    countItem?.let {
                        it.count  = 0
                        EventBus.getDefault().postSticky(it)

                    }

                }

               }
               else
               {
                   Log.i(TAG, "onDialogBuy: no")
               }
           }
           DIRECT->
           {

             var url = "http://rezamotahari1375.ir/s/newshop/basket/checkout.php?reciver_id="+reciveId+"&HTTP_AUTHORIZATION="+TokenContainer.token+"&shipping_price="+shippingPrice.toString()+"&payable_price="+payBale.toString()
            //   var url = "http://digishop.novindevelopers.ir/api/v1/basket/checkout.php?reciver_id="+reciveId+"&HTTP_AUTHORIZATION="+TokenContainer.token+"&shipping_price="+shippingPrice.toString()+"&payable_price=1000"
//               var builder = CustomTabsIntent.Builder()
//               var customTabsIntent : CustomTabsIntent
//               customTabsIntent = builder.build()
//               customTabsIntent.launchUrl(this, Uri.parse(url))
//               finish()

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
               startActivity(intent)
               finish()
           }
       }
    }
}