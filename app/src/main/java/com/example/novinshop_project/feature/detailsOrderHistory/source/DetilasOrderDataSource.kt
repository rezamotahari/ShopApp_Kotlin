package com.example.novinshop_project.feature.detailsOrderHistory.source

import com.example.novinshop_project.data.ResponseDetailsOrder
import io.reactivex.Single

interface DetilasOrderDataSource {
    fun getDetialsOrder(refId:String):Single<ResponseDetailsOrder>
}