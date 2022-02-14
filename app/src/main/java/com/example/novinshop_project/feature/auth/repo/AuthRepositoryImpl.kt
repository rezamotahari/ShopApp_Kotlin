package com.example.novinshop_project.feature.auth.repo

import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.auth.TokenContainer
import com.example.novinshop_project.feature.auth.source.AuthDataSource
import com.example.novinshop_project.feature.auth.source.AuthLocalDataSource
import com.example.novinshop_project.feature.auth.source.RemoteAuthDataSource
import io.reactivex.Single

class AuthRepositoryImpl(val remoteAuthDataSource: AuthDataSource,val localDataSource: AuthLocalDataSource):AuthRepository {
    override fun checkUser(phone: String): Single<ResponseCheckUser>   = remoteAuthDataSource.checkUser(phone)
    override fun registerUser(phone: String, name: String): Single<ResponseRegister>
    {
       return remoteAuthDataSource.registerUser(phone,name).doOnSuccess {
           TokenContainer.updateToken(it.token)
           localDataSource.saveToken(it.token)
       }
    }

    override fun login(phone: String): Single<ResponseLogin> {

        return remoteAuthDataSource.login(phone).doOnSuccess {

            TokenContainer.updateToken(it.token)
            localDataSource.saveToken(it.token)
        }

    }
    override fun loadToken() {
  localDataSource.loadToken()
    }

    override fun checkLogin(): Boolean {
        return  localDataSource.checkLogin()
    }

    override fun addToBookmark(productId_: Int): Single<ResponseAddBookmark>  = remoteAuthDataSource.addToBookmark(productId_)
    override fun addToCart(
        productId: Int,
        colorid: Int,
        sizeId: Int
    ): Single<ResponseInsertCommnet> = remoteAuthDataSource.addToCart(productId,colorid,sizeId)
}