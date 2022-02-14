package com.example.novinshop_project.feature.allAmazing

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseSortAmazing
import com.example.novinshop_project.feature.allAmazing.repo.SortRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class SortViewModel(var sort: Int, val sortRepository: SortRepository) : BaseViewModel() {

    val allAlmazingLiveData = MutableLiveData<List<ResponseSortAmazing>>()
    val selectTitleLiveData = MutableLiveData<Int>()
    val sortTitleArray = arrayOf(R.string.popular, R.string.lowPrice, R.string.highPrice)

    init {
        getAllalamazing()
        selectTitleLiveData.value = sortTitleArray[sort]
    }

    fun getAllalamazing() {
        progressBarLiveData.value = true
        sortRepository.getAllAmazing(sort).singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object :
                DigiShopSingleObserver<List<ResponseSortAmazing>>(compositeDisposable) {
                override fun onSuccess(t: List<ResponseSortAmazing>) {
                    allAlmazingLiveData.value = t
                }
            })
    }

    fun selectedItemDialog(sortId: Int) {
        this.sort = sortId
        this.selectTitleLiveData.value = sortTitleArray[sortId]
        getAllalamazing()
    }

}