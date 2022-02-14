package com.example.novinshop_project.feature.home

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.home.repo.*
import com.example.novinshop_project.utils.DigiShopSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    val bannerRepository: BannerRepository,
    val categoryHomeRepository: CategoryHomeRepository,
    val amazingRepository: AmazingRepository,
    val popularProductRepository: PopularProductRepository,
    val bannersTypeRepository: BannersTypeRepository
) : BaseViewModel() {

    var bannersLiveData = MutableLiveData<List<ResponseBanners>>()
    val categoryLiveData = MutableLiveData<List<ResponseCategoryHome>>()
    val amazingProductLivaData = MutableLiveData<List<ResponseAmazingProduct>>()
    val popularLiveData = MutableLiveData<List<ResponsePopular>>()
    val bannerTypeLiveData = MutableLiveData<List<ResponseBannersType>>()

    init {
        progressBarLiveData.value = true
        bannerRepository.getBanner().
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : DigiShopSingleObserver<List<ResponseBanners>>(compositeDisposable) {
                override fun onSuccess(t: List<ResponseBanners>) {
                    bannersLiveData.value = t
                }
            })
        categoryHomeRepository.getCategoryHome().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                DigiShopSingleObserver<List<ResponseCategoryHome>>(compositeDisposable) {
                override fun onSuccess(t: List<ResponseCategoryHome>) {
                    categoryLiveData.value = t
                }
            })
        amazingRepository.getAmazingProduct().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                DigiShopSingleObserver<List<ResponseAmazingProduct>>(compositeDisposable) {
                override fun onSuccess(t: List<ResponseAmazingProduct>) {
                    amazingProductLivaData.value = t
                }
            })
        popularProductRepository.getPopularProduct().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DigiShopSingleObserver<List<ResponsePopular>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponsePopular>) {
                    popularLiveData.value = t
                }

            })
        bannersTypeRepository.getBannersType().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DigiShopSingleObserver<List<ResponseBannersType>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseBannersType>) {
                    bannerTypeLiveData.value = t
                }

            })


    }
}