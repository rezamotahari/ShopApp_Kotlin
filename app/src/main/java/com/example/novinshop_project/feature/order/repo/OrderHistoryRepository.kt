package com.example.novinshop_project.feature.order.repo

import com.example.novinshop_project.data.ResponseOrderHistory
import io.reactivex.Single

interface OrderHistoryRepository {
    fun getOrderHistory():Single<List<ResponseOrderHistory>>
}