package com.example.novinshop_project.feature.detials.comapre

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseCompare
import com.example.novinshop_project.feature.detials.comapre.repo.CompareProductRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver
import com.example.novinshop_project.utils.PRODUCT_ONE
import com.example.novinshop_project.utils.PRODUCT_TWO

class CompareProductViewModel(bundle: Bundle,val compareProductRepository: CompareProductRepository):BaseViewModel() {

    val compareProductLiveData = MutableLiveData<ResponseCompare>()
    var idOne :Int?=null
    var idTwo :Int?=null
    init {
        idOne = bundle.getInt(PRODUCT_ONE)
        idTwo = bundle.getInt(PRODUCT_TWO)
        progressBarLiveData.value = true
        compareProductRepository.compareProduct(idOne!!,idTwo!!)
            .singleObserver()
            .doFinally {

                progressBarLiveData.value=false
            }
            .subscribe(object :DigiShopSingleObserver<ResponseCompare>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseCompare) {
                    compareProductLiveData.value = t
                }

            })

    }
}