package com.example.novinshop_project.feature.detailsOrderHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.utils.PriceConverter
import com.example.novinshop_project.utils.REFID
import kotlinx.android.synthetic.main.activity_details_order.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class DetailsOrder_Activity : AppCompatActivity() {
    val detilasOrderViewModel:DetilasOrderViewModel by viewModel { parametersOf(intent.getStringExtra(
        REFID))  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_order)

        txt_title_activity.text = "جزئیات تراکنش"
        img_Back.setOnClickListener {
            finish()
        }
        detilasOrderViewModel.detilasOrderLiveData.observe(this)
        {
            Timber.i("$it")
            txt_refId.text = it.refId
            txt_date.text = it.dateTime
            txt_name.text = it.nameFamily
            txt_phone.text = it.phone
            txt_address.text = it.address
            txt_total.text = PriceConverter.priceConverter(it.totalPrice)
            txt_type.text = it.payType

            if (it.status.equals("1"))
            {
                txt_shipping_code.text = "کدی موجود نیست"
                txt_send.text = "در حال آماده سازی"
                progress_send.progress =1
            }
            else if (it.status.equals("2"))
            {
                txt_send.text = "تحویل پست داده شده"
                progress_send.progress =2
                txt_shipping_code.text = it.shippingCode
            }

            txt_shipping.text = PriceConverter.priceConverter(it.shippingPrice)
            rc_order.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            val adapterProductDetilsOrder :AdapterProductDetilsOrder by inject { parametersOf(it.orderDetail)  }
            rc_order.adapter = adapterProductDetilsOrder


        }
    }
}