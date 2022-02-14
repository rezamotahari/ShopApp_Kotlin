package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseAmazingProduct
import io.reactivex.Single

class RemoteAmazingDatasource(val apiService: ApiService):AmazingDataSource {
    override fun getAmazingProduct(): Single<List<ResponseAmazingProduct>>  = apiService.getAmazingProduct()
}