package com.example.novinshop_project.feature.detials.repo

import com.example.novinshop_project.data.ResponseDetailsProduct
import com.example.novinshop_project.feature.detials.source.DetailsProductDataSource
import com.example.novinshop_project.feature.detials.source.RemoteDetilasProductDataSource
import io.reactivex.Single

class DetailsProductRepositoryImpl(val remoteDetilasProductDataSource: DetailsProductDataSource):DetailsProductRepository {
    override fun getDetilsProduct(productId: Int): Single<ResponseDetailsProduct>  = remoteDetilasProductDataSource.getDetilsProduct(productId)
}