package com.example.novinshop_project

import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.feature.auth.TokenContainer
import com.example.novinshop_project.feature.cart.repo.CartListRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver
import org.greenrobot.eventbus.EventBus

class MainViewModel(val cartListRepository:CartListRepository):BaseViewModel() {

    fun getCountItem()
    {
        if (!TokenContainer.token.isNullOrEmpty())
        {
            cartListRepository.getCountItem().singleObserver()
                .subscribe(object :DigiShopSingleObserver<ResponseCount>(compositeDisposable){
                    override fun onSuccess(t: ResponseCount) {
                        EventBus.getDefault().postSticky(t)
                    }

                })
        }
    }

}