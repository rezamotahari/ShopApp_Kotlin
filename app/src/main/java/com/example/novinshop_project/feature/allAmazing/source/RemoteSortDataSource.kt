package com.example.novinshop_project.feature.allAmazing.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseSortAmazing
import io.reactivex.Single

class RemoteSortDataSource(val apiService: ApiService):SortDataSource {
    override fun getAllAmazing(sortId: Int): Single<List<ResponseSortAmazing>>  = apiService.getAllAmazing(sortId)
}