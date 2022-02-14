package com.example.novinshop_project.feature.comment.repo.insertComment

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseInsertCommnet
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.ResponseScoreItemCommnet
import com.example.novinshop_project.feature.comment.repo.insertComment.repo.InsertCommnetRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class InsertCommnetViewModel(
    val productId: Int,
    val insertCommnetRepository: InsertCommnetRepository
) : BaseViewModel() {

    val insertCommnetLiveData = MutableLiveData<ResponseInsertCommnet>()
    val scoreinsertCommnetLiveData = MutableLiveData<ResponseScoreInsert>()

    init {
        progressBarLiveData.value = true
        insertCommnetRepository.getStatusScore(productId).singleObserver()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : DigiShopSingleObserver<ResponseScoreInsert>(compositeDisposable) {
                override fun onSuccess(t: ResponseScoreInsert) {
                    scoreinsertCommnetLiveData.value = t
                }
            })
    }

    fun insertCommnet(
        productId: Int,
        content: String,
        title: String,
        posotive: String,
        negative: String,
        advice: String
    ) {

        insertCommnetRepository.insertCommnet(productId, content, title, posotive, negative, advice)
            .singleObserver()
            .subscribe(object : DigiShopSingleObserver<ResponseInsertCommnet>(compositeDisposable) {
                override fun onSuccess(t: ResponseInsertCommnet) {
                    insertCommnetLiveData.value = t
                }

            })

    }

    fun insertCommnetPro(
        productId: Int,
        content: String,
        title: String,
        posotive: String,
        negative: String,
        advice: String, score: String
    ) {

        insertCommnetRepository.insertCommnetPro(
            productId,
            content,
            title,
            posotive,
            negative,
            advice,
            score
        )
            .singleObserver()
            .subscribe(object : DigiShopSingleObserver<ResponseInsertCommnet>(compositeDisposable) {
                override fun onSuccess(t: ResponseInsertCommnet) {
                    insertCommnetLiveData.value = t
                }

            })

    }
}