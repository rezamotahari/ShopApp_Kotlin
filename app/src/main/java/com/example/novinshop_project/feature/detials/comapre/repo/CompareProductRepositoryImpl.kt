package com.example.novinshop_project.feature.detials.comapre.repo

import com.example.novinshop_project.data.ResponseCompare
import com.example.novinshop_project.feature.detials.comapre.source.CompareProductDataSource
import com.example.novinshop_project.feature.detials.comapre.source.RemoteCompareDataSource
import io.reactivex.Single

class CompareProductRepositoryImpl(val remoteCompareDataSource: CompareProductDataSource):CompareProductRepository {
    override fun compareProduct(
        productIdOne: Int,
        productIdTwo: Int
    ): Single<ResponseCompare>  = remoteCompareDataSource.compareProduct(productIdOne,productIdTwo)
}