package com.example.novinshop_project.feature.search.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseSearch
import io.reactivex.Single

class RemoteSearchDataSource(val apiService: ApiService):SearchDataSource {
    override fun serach(keyword: String): Single<ResponseSearch>  = apiService.serach(keyword)
}