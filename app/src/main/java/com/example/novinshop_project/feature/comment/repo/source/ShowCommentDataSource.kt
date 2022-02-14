package com.example.novinshop_project.feature.comment.repo.source

import com.example.novinshop_project.data.ResponseRating
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.Resps
import io.reactivex.Single

interface ShowCommentDataSource {
    fun getShowRatingCommnet(productId:Int):Single<List<ResponseRating>>

    fun getShowCommnet(productId: Int):Single<List<Resps>>

}