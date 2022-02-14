package com.example.novinshop_project.feature.cart.repo

import com.example.novinshop_project.data.ResponseCartList
import com.example.novinshop_project.data.ResponseChangeCountItem
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.data.ResponseMessage
import com.example.novinshop_project.feature.cart.source.CartListDataSource
import com.example.novinshop_project.feature.cart.source.RemoteCartListDataSource
import io.reactivex.Single

class CartListRepositpryImpl(val remoteCartListDataSource: CartListDataSource):CartListRepository {
    override fun getCartList(): Single<ResponseCartList>  = remoteCartListDataSource.getCartList()
    override fun getCountItem(): Single<ResponseCount> = remoteCartListDataSource.getCountItem()
    override fun removeItem(itemId: Int): Single<ResponseMessage>  = remoteCartListDataSource.removeItem(itemId)
    override fun changeCountItem(itemId: Int, count: Int): Single<ResponseChangeCountItem>  = remoteCartListDataSource.changeCountItem(itemId,count)
}