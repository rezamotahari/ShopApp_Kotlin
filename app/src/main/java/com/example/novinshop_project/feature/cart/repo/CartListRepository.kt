package com.example.novinshop_project.feature.cart.repo

import com.example.novinshop_project.data.ResponseCartList
import com.example.novinshop_project.data.ResponseChangeCountItem
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.data.ResponseMessage
import io.reactivex.Single

interface CartListRepository {
    fun getCartList():Single<ResponseCartList>
    fun getCountItem():Single<ResponseCount>
    fun removeItem( itemId:Int):Single<ResponseMessage>
    fun changeCountItem( itemId:Int,count:Int):Single<ResponseChangeCountItem>
}