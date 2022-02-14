package com.example.novinshop_project.feature.detials

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseDetailsProduct
import com.example.novinshop_project.feature.detials.repo.DetailsProductRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(val produtcid: Int, val detailsProductRepository: DetailsProductRepository) :
    BaseViewModel() {

    val detialsProductLiveDta = MutableLiveData<ResponseDetailsProduct>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        progressBarLiveData.value = true
        productIdLiveData.value = produtcid
        detailsProductRepository.getDetilsProduct(productIdLiveData.value!!)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object :
                DigiShopSingleObserver<ResponseDetailsProduct>(compositeDisposable) {
                override fun onSuccess(t: ResponseDetailsProduct) {
                    detialsProductLiveDta.value = t
                }
            })
    }
}