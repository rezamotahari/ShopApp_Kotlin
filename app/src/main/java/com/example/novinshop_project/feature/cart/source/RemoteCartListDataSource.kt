package com.example.novinshop_project.feature.cart.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseCartList
import com.example.novinshop_project.data.ResponseChangeCountItem
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.data.ResponseMessage
import io.reactivex.Single

class RemoteCartListDataSource(val apiService: ApiService):CartListDataSource {
    override fun getCartList(): Single<ResponseCartList> = apiService.getCartList()
    override fun getCountItem(): Single<ResponseCount>  = apiService.getCountItem()
    override fun removeItem(itemId: Int): Single<ResponseMessage> = apiService.removeItem(itemId)
    override fun changeCountItem(itemId: Int, count: Int): Single<ResponseChangeCountItem>  = apiService.changeCountItem(itemId,count)
}