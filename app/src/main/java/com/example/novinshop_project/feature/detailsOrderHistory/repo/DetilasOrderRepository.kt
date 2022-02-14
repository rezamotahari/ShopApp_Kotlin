package com.example.novinshop_project.feature.detailsOrderHistory.repo

import com.example.novinshop_project.data.ResponseDetailsOrder
import io.reactivex.Single

interface DetilasOrderRepository {
    fun getDetialsOrder(refId:String):Single<ResponseDetailsOrder>
}