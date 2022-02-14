package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponsePopular
import com.example.novinshop_project.feature.home.source.PopularProductDataSource
import io.reactivex.Single

class PopualarProductRepositoryImp(val remotePopualarProductDataSource: PopularProductDataSource):PopularProductRepository {
    override fun getPopularProduct(): Single<List<ResponsePopular>>  = remotePopualarProductDataSource.getPopularProduct()
}