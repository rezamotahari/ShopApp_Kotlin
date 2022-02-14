package com.example.novinshop_project.feature.address

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseAddAddress
import com.example.novinshop_project.data.ResponseAddress
import com.example.novinshop_project.feature.address.repo.AddressRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver
import com.google.gson.JsonObject

class AddressViewModel(val addressRepository: AddressRepository):BaseViewModel() {
    val addressRepositoryLiveData = MutableLiveData<List<ResponseAddress>>()
    val addAddressLiveData = MutableLiveData<ResponseAddAddress>()

    init {

    getAddress()
    }
fun getAddress()
{
    progressBarLiveData.value = true
    addressRepository.getAddress().singleObserver()
        .doFinally {
            progressBarLiveData.value = false
        }
        .subscribe(object :DigiShopSingleObserver<List<ResponseAddress>>(compositeDisposable)
        {
            override fun onSuccess(t: List<ResponseAddress>) {
                addressRepositoryLiveData.value = t
            }

        })
}
    fun addAddress(address: JsonObject)
    {
        progressBarLiveData.value = true
        addressRepository.addAddress(address).singleObserver()
            .doFinally { progressBarLiveData.value=false }
            .subscribe(object :DigiShopSingleObserver<ResponseAddAddress>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseAddAddress) {
                    addAddressLiveData.value = t
                }

            })
    }
}