package com.example.novinshop_project.feature.category.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseCategory
import com.example.novinshop_project.feature.category.repo.CategoryRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class CategoryViewModel(val categoryRepository: CategoryRepository):BaseViewModel() {

    val categoryLiveData = MutableLiveData<List<ResponseCategory>>()
    init {
        progressBarLiveData.value = true
        categoryRepository.getCategory().singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            . subscribe(object : DigiShopSingleObserver<List<ResponseCategory>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseCategory>) {
                    categoryLiveData.value = t
                }

            })

    }
}