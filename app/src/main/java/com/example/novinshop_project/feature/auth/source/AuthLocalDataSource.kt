package com.example.novinshop_project.feature.auth.source

import android.content.SharedPreferences
import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.auth.TokenContainer
import io.reactivex.Single

class AuthLocalDataSource(val sharedPreferences: SharedPreferences) : AuthDataSource {
    override fun checkUser(phone: String): Single<ResponseCheckUser> {
        TODO("Not yet implemented")
    }

    override fun registerUser(phone: String, name: String): Single<ResponseRegister> {
        TODO("Not yet implemented")
    }

    override fun login(phone: String): Single<ResponseLogin> {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().apply {

            putString("token", token)
        }.apply()
    }

    override fun loadToken() {

        TokenContainer.updateToken(sharedPreferences.getString("token", null))
    }

    override fun checkLogin(): Boolean {

        if (sharedPreferences.getString("token", "") != "") {
            return true
        } else {
            return false
        }

    }

    override fun addToBookmark(productId_: Int): Single<ResponseAddBookmark> {
        TODO("Not yet implemented")
    }

    override fun addToCart(
        productId: Int,
        colorid: Int,
        sizeId: Int
    ): Single<ResponseInsertCommnet> {
        TODO("Not yet implemented")
    }
}