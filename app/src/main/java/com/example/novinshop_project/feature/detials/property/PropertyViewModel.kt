package com.example.novinshop_project.feature.detials.property

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseProperty
import com.example.novinshop_project.feature.detials.property.repo.PropertyRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class PropertyViewModel(val productId:Int ,val propertyRepository: PropertyRepository)
    :BaseViewModel(){

    val propertyLiveData = MutableLiveData<List<ResponseProperty>>()
    val productIdLiveData = MutableLiveData<Int>()
    init {
        progressBarLiveData.value = true
        productIdLiveData.value = productId
        propertyRepository.getProperty(productIdLiveData.value!!)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object :DigiShopSingleObserver<List<ResponseProperty>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseProperty>) {
                    propertyLiveData.value = t
                }

            })
    }
}