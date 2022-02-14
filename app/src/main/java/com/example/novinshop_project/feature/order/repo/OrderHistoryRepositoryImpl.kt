package com.example.novinshop_project.feature.order.repo

import com.example.novinshop_project.data.ResponseOrderHistory
import com.example.novinshop_project.feature.order.source.OrderHistoryDataSource
import com.example.novinshop_project.feature.order.source.RemoteOrderHistory
import io.reactivex.Single

class OrderHistoryRepositoryImpl(val remoteOrderHistory: OrderHistoryDataSource):OrderHistoryRepository {
    override fun getOrderHistory(): Single<List<ResponseOrderHistory>>  = remoteOrderHistory.getOrderHistory()
}