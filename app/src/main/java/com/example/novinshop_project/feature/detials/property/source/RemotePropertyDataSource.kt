package com.example.novinshop_project.feature.detials.property.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseProperty
import io.reactivex.Single

class RemotePropertyDataSource(val apiService: ApiService):PropertyDataSource {
    override fun getProperty(productId: Int): Single<List<ResponseProperty>> = apiService.getProperty(productId)
}