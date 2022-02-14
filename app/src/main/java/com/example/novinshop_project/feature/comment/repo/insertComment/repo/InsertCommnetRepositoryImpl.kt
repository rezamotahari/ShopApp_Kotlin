package com.example.novinshop_project.feature.comment.repo.insertComment.repo

import com.example.novinshop_project.data.ResponseInsertCommnet
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.ResponseScoreItemCommnet
import com.example.novinshop_project.feature.comment.repo.insertComment.source.InsertCommnetDataSource
import com.example.novinshop_project.feature.comment.repo.insertComment.source.RemoteInsertDataSource
import io.reactivex.Single

class InsertCommnetRepositoryImpl(val remoteInsertDataSource: InsertCommnetDataSource):InsertCommnetRepository {
    override fun insertCommnet(
        productId: Int,
        content: String,
        title: String,
        posotive: String,
        negative: String,
        advice: String
    ): Single<ResponseInsertCommnet>  = remoteInsertDataSource.insertCommnet(productId,content,title
    ,posotive,negative,advice)

    override fun insertCommnetPro(
        productId: Int,
        content: String,
        title: String,
        posotive: String,
        negative: String,
        advice: String,
        score: String
    ): Single<ResponseInsertCommnet>  = remoteInsertDataSource.insertCommnetPro(productId,content,title,posotive,negative,advice,score)

    override fun getStatusScore(productId: Int): Single<ResponseScoreInsert>  = remoteInsertDataSource.getStatusScore(productId)

}