package com.example.novinshop_project.feature.detials.comapre

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseCategoryCompare
import com.example.novinshop_project.feature.detials.comapre.repo.CategoryComapreRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class CategoryCoampreViewModel(
    val productId: Int,
    val categoryComapreRepository: CategoryComapreRepository
) : BaseViewModel() {

    val categoryComapreLiveData = MutableLiveData<List<ResponseCategoryCompare>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        progressBarLiveData.value = true
        productIdLiveData.value = productId
        categoryComapreRepository.getCategoryComapre(productIdLiveData.value!!)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value=false
            }
            .subscribe(object : DigiShopSingleObserver<List<ResponseCategoryCompare>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseCategoryCompare>) {
                    categoryComapreLiveData.value = t
                }

            })

    }
}