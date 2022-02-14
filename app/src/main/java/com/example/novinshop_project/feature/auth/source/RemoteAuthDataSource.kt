package com.example.novinshop_project.feature.auth.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.*
import io.reactivex.Single

class RemoteAuthDataSource(val apiService: ApiService) : AuthDataSource {
    override fun checkUser(phone: String): Single<ResponseCheckUser> = apiService.checkUser(phone)
    override fun registerUser(phone: String, name: String): Single<ResponseRegister> = apiService.registerUser(phone, name)

    override fun login(phone: String): Single<ResponseLogin> = apiService.login(phone)
    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun loadToken() {
        TODO("Not yet implemented")
    }

    override fun checkLogin(): Boolean {
        TODO("Not yet implemented")
    }

    override fun addToBookmark(productId_: Int): Single<ResponseAddBookmark>  = apiService.addToBookMark(productId_)
    override fun addToCart(
        productId: Int,
        colorid: Int,
        sizeId: Int
    ): Single<ResponseInsertCommnet>  =  apiService.addToCart(productId,colorid,sizeId)
}
