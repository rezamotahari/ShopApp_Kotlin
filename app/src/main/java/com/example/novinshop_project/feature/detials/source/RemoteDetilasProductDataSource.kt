package com.example.novinshop_project.feature.detials.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseDetailsProduct
import io.reactivex.Single

class RemoteDetilasProductDataSource(val apiService: ApiService):DetailsProductDataSource {
    override fun getDetilsProduct(productId: Int): Single<ResponseDetailsProduct>  = apiService.getDetailsProduct(productId)
}