package com.example.novinshop_project.feature.detials.comapre.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseCompare
import io.reactivex.Single

class RemoteCompareDataSource(val apiService: ApiService):CompareProductDataSource {
    override fun compareProduct(
        productIdOne: Int,
        productIdTwo: Int
    ): Single<ResponseCompare>  = apiService.compareProduct(productIdOne,productIdTwo)
}