package com.example.novinshop_project.feature.nextlevel.repo

import com.example.novinshop_project.data.ResponseCheckOutList
import com.example.novinshop_project.data.ResponseTrancation
import io.reactivex.Single

interface CheckOutListRepository {
    fun getCheckOutList():Single<ResponseCheckOutList>
    fun insertTrancation(reciveId:String,refId:String,shippingPrice:String,payblaPrice:String):Single<ResponseTrancation>
}