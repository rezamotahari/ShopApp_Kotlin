package com.example.novinshop_project.feature.home.subCatLevel

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseSubCatLevel
import com.example.novinshop_project.feature.home.repo.SubCatLevelRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class SubcatLevelViewModel(val subcatId:Int,val subCatLevelRepository: SubCatLevelRepository):BaseViewModel() {

    val subCatLiveData = MutableLiveData<List<ResponseSubCatLevel>>()
    val subcatIdLiveData = MutableLiveData<Int>()

    init {
        subcatIdLiveData.value = subcatId
        progressBarLiveData.value = true
        subCatLevelRepository.getSubcatLevel(subcatIdLiveData.value!!).singleObserver().doFinally {

            progressBarLiveData.value = false
        }.subscribe(object : DigiShopSingleObserver<List<ResponseSubCatLevel>>(compositeDisposable)
        {
            override fun onSuccess(t: List<ResponseSubCatLevel>) {

                subCatLiveData.value = t
            }

        })

    }

}