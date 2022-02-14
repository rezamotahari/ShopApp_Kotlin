package com.example.novinshop_project.feature.comment.repo

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseRating
import com.example.novinshop_project.data.Resps
import com.example.novinshop_project.feature.comment.repo.repo.ShowCommentRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class ShowRatingViewModel(val productId:Int ,val repository: ShowCommentRepository)
    :BaseViewModel(){

    val showRatingLiveData = MutableLiveData<List<ResponseRating>>()
    val showCommnetLiveDta = MutableLiveData<List<Resps>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        productIdLiveData.value =productId
        progressBarLiveData.value = true
        repository.getShowRatingCommnet(productIdLiveData.value!!).
                singleObserver()
            .doFinally {  progressBarLiveData.value = false}
            .subscribe(object :DigiShopSingleObserver<List<ResponseRating>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseRating>) {
                 showRatingLiveData.value = t
                }

            })


        repository.getShowCommnet(productIdLiveData.value!!)
            .singleObserver()
            .subscribe(object :DigiShopSingleObserver<List<Resps>>(compositeDisposable)
            {
                override fun onSuccess(t: List<Resps>) {
                    showCommnetLiveDta.value =t
                }

            })
    }
}