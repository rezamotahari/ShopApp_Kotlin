package com.example.novinshop_project.feature.nextlevel.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseCheckOutList
import com.example.novinshop_project.data.ResponseTrancation
import io.reactivex.Single

class RemoteCheckOutDataSource(val apiService: ApiService):CheckOutListDataSource {
    override fun getCheckOutList(): Single<ResponseCheckOutList>  = apiService.checkOutList()
    override fun insertTrancation(
        reciveId: String,
        refId: String,
        shippingPrice: String,
        payblaPrice: String
    ): Single<ResponseTrancation>  = apiService.insertTrancation(reciveId,refId,shippingPrice,payblaPrice)
}