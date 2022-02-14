package com.example.novinshop_project.feature.detailsOrderHistory.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseDetailsOrder
import io.reactivex.Single

class RemoteDetailsOrederDataSource(val apiService: ApiService):DetilasOrderDataSource {
    override fun getDetialsOrder(refId: String): Single<ResponseDetailsOrder> = apiService.getDetailsOrder(refId)
}