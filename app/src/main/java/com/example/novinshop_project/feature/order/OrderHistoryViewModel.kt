package com.example.novinshop_project.feature.order

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseOrderHistory
import com.example.novinshop_project.feature.order.repo.OrderHistoryRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class OrderHistoryViewModel(val orderHistoryRepository: OrderHistoryRepository):BaseViewModel() {
    val orderHistoryLiveData= MutableLiveData<List<ResponseOrderHistory>>()

    init {
        progressBarLiveData.value = true
        orderHistoryRepository.getOrderHistory().singleObserver().doFinally {
            progressBarLiveData.value= false
        }
            .subscribe(object :DigiShopSingleObserver<List<ResponseOrderHistory>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseOrderHistory>) {
                    orderHistoryLiveData.value = t
                }

            })

    }
}