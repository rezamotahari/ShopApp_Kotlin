package com.example.novinshop_project.feature.nextlevel.repo

import com.example.novinshop_project.data.ResponseCheckOutList
import com.example.novinshop_project.data.ResponseTrancation
import com.example.novinshop_project.feature.nextlevel.source.CheckOutListDataSource
import com.example.novinshop_project.feature.nextlevel.source.RemoteCheckOutDataSource
import io.reactivex.Single

class CheckOutListRepositpryImpl(val remoteCheckOutDataSource: CheckOutListDataSource):CheckOutListRepository {
    override fun getCheckOutList(): Single<ResponseCheckOutList> = remoteCheckOutDataSource.getCheckOutList()
    override fun insertTrancation(
        reciveId: String,
        refId: String,
        shippingPrice: String,
        payblaPrice: String
    ): Single<ResponseTrancation> = remoteCheckOutDataSource.insertTrancation(reciveId,refId,shippingPrice,payblaPrice)

}