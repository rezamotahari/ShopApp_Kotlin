package com.example.novinshop_project.feature.auth.repo

import com.example.novinshop_project.data.*
import io.reactivex.Single

interface AuthRepository {

    fun checkUser(phone:String):Single<ResponseCheckUser>
    fun registerUser(phone:String, name:String):Single<ResponseRegister>
    fun login(phone:String):Single<ResponseLogin>
    fun loadToken()
    fun checkLogin():Boolean
    fun addToBookmark(productId_:Int):Single<ResponseAddBookmark>
    fun addToCart(productId: Int,colorid:Int,sizeId:Int):Single<ResponseInsertCommnet>
}