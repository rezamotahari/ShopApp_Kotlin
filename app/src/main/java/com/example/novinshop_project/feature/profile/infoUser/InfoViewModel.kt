package com.example.novinshop_project.feature.profile.infoUser

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseInfoUser
import com.example.novinshop_project.feature.profile.infoUser.repo.InfoRespository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class InfoViewModel(val infoRespository: InfoRespository):BaseViewModel() {
    val infoLiveData = MutableLiveData<ResponseInfoUser>()
    init {
        progressBarLiveData.value = true
        infoRespository.getInfoUser().singleObserver()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object :DigiShopSingleObserver<ResponseInfoUser>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseInfoUser) {
                    infoLiveData.value = t
                }
            })

    }
}