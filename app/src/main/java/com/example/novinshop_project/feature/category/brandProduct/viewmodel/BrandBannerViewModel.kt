package com.example.novinshop_project.feature.category.brandProduct.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseBrandBanner
import com.example.novinshop_project.data.ResponseBrandProduct
import com.example.novinshop_project.feature.category.brandProduct.repo.BrandBannerRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class BrandBannerViewModel(val brandId:Int ,val bannerRepository: BrandBannerRepository):BaseViewModel() {

    val bannerBrandLiveData = MutableLiveData<ResponseBrandBanner>()
    val brandProductLiveData = MutableLiveData<List<ResponseBrandProduct>>()
    val bannerIdLiveData = MutableLiveData<Int>()

    init {
        progressBarLiveData.value = true
        bannerIdLiveData.value = brandId
        bannerRepository.getBannerBrand(bannerIdLiveData.value!!)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : DigiShopSingleObserver<ResponseBrandBanner>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseBrandBanner) {
                    bannerBrandLiveData.value = t

                }


            })

        bannerRepository.getBrandProduct(bannerIdLiveData.value!!)
            .singleObserver()
            .subscribe(object :DigiShopSingleObserver<List<ResponseBrandProduct>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseBrandProduct>) {
                    brandProductLiveData.value = t
                }

            })




    }
}