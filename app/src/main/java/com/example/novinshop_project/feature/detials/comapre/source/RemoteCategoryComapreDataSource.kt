package com.example.novinshop_project.feature.detials.comapre.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseCategoryCompare
import io.reactivex.Single

class RemoteCategoryComapreDataSource(val  apiService: ApiService):CategoryComapreDataSource {
    override fun getCategoryComapre(productId: Int): Single<List<ResponseCategoryCompare>>  = apiService.getCategoryComapre(productId)
}