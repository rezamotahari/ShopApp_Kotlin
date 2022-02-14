package com.example.novinshop_project.feature.comment.repo.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseRating
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.Resps
import io.reactivex.Single

class RemoteShowCommnetDataSource(val  apiService: ApiService):ShowCommentDataSource {
    override fun getShowRatingCommnet(productId:Int): Single<List<ResponseRating>>  = apiService.getShowRatingCommnet(productId)

    override fun getShowCommnet(productId: Int): Single<List<Resps>>  = apiService.getShowCommnet(productId)
}