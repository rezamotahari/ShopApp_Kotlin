package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseCategoryHome
import io.reactivex.Single

class RemoteCategoryHomeDataSource(val apiService: ApiService):CategoryHomeDataSource {
    override fun getCategoryHome(): Single<List<ResponseCategoryHome>> = apiService.getCategoryHome()
}