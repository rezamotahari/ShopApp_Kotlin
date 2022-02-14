package com.example.novinshop_project.feature.detailsOrderHistory

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseDetailsOrder
import com.example.novinshop_project.feature.detailsOrderHistory.repo.DetilasOrderRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class DetilasOrderViewModel(val refId:String,val repository: DetilasOrderRepository):BaseViewModel() {

    val detilasOrderLiveData =MutableLiveData<ResponseDetailsOrder>()
    val refIdLiveData = MutableLiveData<String>()
    init {
        refIdLiveData.value = refId
        progressBarLiveData.value = true
        repository.getDetialsOrder(refIdLiveData.value!!)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object :DigiShopSingleObserver<ResponseDetailsOrder>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseDetailsOrder) {
                    detilasOrderLiveData.value = t
                }
            })
    }
}