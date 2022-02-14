package com.example.novinshop_project.feature.nextlevel

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseCheckOutList
import com.example.novinshop_project.data.ResponseTrancation
import com.example.novinshop_project.feature.nextlevel.repo.CheckOutListRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class CheckOutListViewModel(val checkOutListRepository: CheckOutListRepository):BaseViewModel() {

    val checkOutListLiveData = MutableLiveData<ResponseCheckOutList>()
    val trancationLiveData = MutableLiveData<ResponseTrancation>()

    init {
        progressBarLiveData.value = true
        checkOutListRepository.getCheckOutList().singleObserver().doFinally {

            progressBarLiveData.value=false
        }.subscribe(object :DigiShopSingleObserver<ResponseCheckOutList>(compositeDisposable)
        {
            override fun onSuccess(t: ResponseCheckOutList) {
                checkOutListLiveData.value = t
            }

        })

    }
    fun insertTrancation(reciveId:String,refId:String,shippingPrice:String,payblaPrice:String)
    {
        progressBarLiveData.value = true
        checkOutListRepository.insertTrancation(reciveId,refId,shippingPrice,payblaPrice)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value=false
            }
            .subscribe(object :DigiShopSingleObserver<ResponseTrancation>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseTrancation) {
                    trancationLiveData.value = t
                }

            })
    }
}