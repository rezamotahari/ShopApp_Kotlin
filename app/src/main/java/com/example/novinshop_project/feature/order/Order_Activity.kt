package com.example.novinshop_project.feature.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.feature.detailsOrderHistory.DetailsOrder_Activity
import com.example.novinshop_project.feature.order.adapter.AdapterOrderHistory
import com.example.novinshop_project.utils.REFID
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Order_Activity : BaseActivity() ,AdapterOrderHistory.OnClickOrderHistory{
    val orderHistoryViewModel: OrderHistoryViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        txt_title_activity.text = getString(R.string.cloud)

        val countItem = EventBus.getDefault().getStickyEvent(ResponseCount::class.java)
        countItem?.let {
            it.count  = 0
            EventBus.getDefault().postSticky(it)

        }

        img_Back.setOnClickListener {

            finish()
        }
        orderHistoryViewModel.orderHistoryLiveData.observe(this)
        {
            Timber.i("$it")
            val adapterOrderHistory: AdapterOrderHistory by inject { parametersOf(it) }
            rc_order_history.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rc_order_history.adapter = adapterOrderHistory
            adapterOrderHistory.setOnclickOrderHistory(this)
        }
        orderHistoryViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }

    }

    override fun onClickOrder(refId: String) {
     startActivity(Intent(this,DetailsOrder_Activity::class.java).apply {
         putExtra(REFID,refId)
     })
    }
}