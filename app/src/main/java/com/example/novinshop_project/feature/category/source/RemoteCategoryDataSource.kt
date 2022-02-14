package com.example.novinshop_project.feature.category.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseCategory
import io.reactivex.Single

class RemoteCategoryDataSource(val apiService: ApiService):CategoryDataSource {
    override fun getCategory(): Single<List<ResponseCategory>>  = apiService.getCategory()
}