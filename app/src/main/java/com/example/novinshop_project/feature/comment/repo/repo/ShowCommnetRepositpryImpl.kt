package com.example.novinshop_project.feature.comment.repo.repo

import com.example.novinshop_project.data.ResponseRating
import com.example.novinshop_project.data.ResponseScoreInsert
import com.example.novinshop_project.data.Resps
import com.example.novinshop_project.feature.comment.repo.source.RemoteShowCommnetDataSource
import io.reactivex.Single

class ShowCommnetRepositpryImpl(val remoteShowCommnetDataSource: RemoteShowCommnetDataSource):ShowCommentRepository {
    override fun getShowRatingCommnet(productId: Int): Single<List<ResponseRating>>  = remoteShowCommnetDataSource.getShowRatingCommnet(productId)

    override fun getShowCommnet(productId: Int): Single<List<Resps>>  = remoteShowCommnetDataSource.getShowCommnet(productId)
}