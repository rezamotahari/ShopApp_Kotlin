package com.example.novinshop_project.feature.comment.repo.insertComment.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseInsertCommnet
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.ResponseScoreItemCommnet
import io.reactivex.Single

class RemoteInsertDataSource(val apiService: ApiService):InsertCommnetDataSource {
    override fun insertCommnet(
        productId: Int,
        content: String,
        title: String,
        posotive: String,
        negative: String,
        advice: String
    ): Single<ResponseInsertCommnet>  = apiService.insertComment(productId,content,title,posotive,negative,advice)

    override fun getStatusScore(productId: Int): Single<ResponseScoreInsert>  = apiService.getStatusScore(productId)
    override fun insertCommnetPro(
        productId: Int,
        content: String,
        title: String,
        posotive: String,
        negative: String,
        advice: String,
        score: String
    ): Single<ResponseInsertCommnet>  = apiService.insertCommentPro(productId,content,title,posotive,negative,advice,score)


}