package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseAmazingProduct
import com.example.novinshop_project.feature.home.source.AmazingDataSource
import io.reactivex.Single

class AmazingRepositoryImp(val remoteAmazingDatasource:AmazingDataSource):AmazingRepository {
    override fun getAmazingProduct(): Single<List<ResponseAmazingProduct>>  = remoteAmazingDatasource.getAmazingProduct()
}