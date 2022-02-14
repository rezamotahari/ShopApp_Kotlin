package com.example.novinshop_project.feature.order.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseOrderHistory
import io.reactivex.Single

class RemoteOrderHistory(val apiService: ApiService):OrderHistoryDataSource {
    override fun getOrderHistory(): Single<List<ResponseOrderHistory>> = apiService.getOrderHistory()
}