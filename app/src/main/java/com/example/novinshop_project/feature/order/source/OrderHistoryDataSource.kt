package com.example.novinshop_project.feature.order.source

import com.example.novinshop_project.data.ResponseOrderHistory
import io.reactivex.Single

interface OrderHistoryDataSource {
    fun getOrderHistory():Single<List<ResponseOrderHistory>>
}