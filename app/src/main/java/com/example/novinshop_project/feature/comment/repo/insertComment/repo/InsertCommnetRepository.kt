package com.example.novinshop_project.feature.comment.repo.insertComment.repo

import com.example.novinshop_project.data.ResponseInsertCommnet
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.ResponseScoreItemCommnet
import io.reactivex.Single
import retrofit2.http.Field

interface InsertCommnetRepository {

    fun insertCommnet( productId:Int,  content:String
                      ,  title:String,  posotive:String
                      ,  negative:String,  advice:String) :Single<ResponseInsertCommnet>

    fun insertCommnetPro( productId:Int,  content:String
                       ,  title:String,  posotive:String
                       ,  negative:String,  advice:String,score:String) :Single<ResponseInsertCommnet>

    fun getStatusScore(productId: Int):Single<ResponseScoreInsert>

}