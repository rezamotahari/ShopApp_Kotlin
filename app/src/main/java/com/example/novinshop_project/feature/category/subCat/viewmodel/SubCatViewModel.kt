package com.example.novinshop_project.feature.category.subCat.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseBrands
import com.example.novinshop_project.data.ResponsePopularCat
import com.example.novinshop_project.data.ResponseSubCat
import com.example.novinshop_project.feature.category.subCat.repo.SubCatRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class SubCatViewModel(val catId:Int,val subCatRepository: SubCatRepository) :BaseViewModel() {


   val  subCatLiveData = MutableLiveData<List<ResponseSubCat>>()
   val  brandsLiveData = MutableLiveData<List<ResponseBrands>>()
   val  popularCatLiveData = MutableLiveData<List<ResponsePopularCat>>()
   val  catIdLiveData = MutableLiveData<Int>()

    init {
        catIdLiveData.value = catId
        progressBarLiveData.value =true
        subCatRepository.getSubCat(catIdLiveData.value!!)
            .singleObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object :DigiShopSingleObserver<List<ResponseSubCat>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseSubCat>) {
                    subCatLiveData.value = t
                }

            })

        subCatRepository.getBrands(catIdLiveData.value!!)
            .singleObserver()
            .subscribe(object : DigiShopSingleObserver<List<ResponseBrands>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseBrands>) {
                    brandsLiveData.value = t
                }

            })
        subCatRepository.getPopularCat(catIdLiveData.value!!)
            .singleObserver()
            .subscribe(object :DigiShopSingleObserver<List<ResponsePopularCat>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponsePopularCat>) {
                    popularCatLiveData.value = t
                }

            })

    }
}