package com.example.novinshop_project.feature.detailsOrderHistory.repo

import com.example.novinshop_project.data.ResponseDetailsOrder
import com.example.novinshop_project.feature.detailsOrderHistory.source.DetilasOrderDataSource
import com.example.novinshop_project.feature.detailsOrderHistory.source.RemoteDetailsOrederDataSource
import com.example.novinshop_project.feature.order.source.RemoteOrderHistory
import io.reactivex.Single

class DetilasOrderRepositoryImpl(val remoteDetailsOrederDataSource: DetilasOrderDataSource):DetilasOrderRepository {
    override fun getDetialsOrder(refId: String): Single<ResponseDetailsOrder>  = remoteDetailsOrederDataSource.getDetialsOrder(refId)
}