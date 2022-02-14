package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponsePopular
import io.reactivex.Single

class RemotePopualarProductDataSource(val apiService: ApiService):PopularProductDataSource {
    override fun getPopularProduct(): Single<List<ResponsePopular>>  = apiService.getPopularProduct()
}