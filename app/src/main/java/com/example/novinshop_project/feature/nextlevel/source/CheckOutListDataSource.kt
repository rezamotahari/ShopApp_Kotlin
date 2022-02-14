package com.example.novinshop_project.feature.nextlevel.source

import com.example.novinshop_project.data.ResponseCheckOutList
import com.example.novinshop_project.data.ResponseTrancation
import io.reactivex.Single

interface CheckOutListDataSource {
    fun getCheckOutList():Single<ResponseCheckOutList>
    fun insertTrancation(reciveId:String,refId:String,shippingPrice:String,payblaPrice:String):Single<ResponseTrancation>

}