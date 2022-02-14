package com.example.novinshop_project.feature.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseSearch
import com.example.novinshop_project.feature.search.repo.SearchRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class SearchViewModel(val searchRepository: SearchRepository) : BaseViewModel() {

    val searchLiveData = MutableLiveData<ResponseSearch>()

    fun search(keyword: String) {
        progressBarLiveData.value = false
        searchRepository.serach(keyword).singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : DigiShopSingleObserver<ResponseSearch>(compositeDisposable) {
                override fun onSuccess(t: ResponseSearch) {
                    searchLiveData.value = t
                }
            })

    }

}