package com.example.novinshop_project.feature.auth

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.auth.repo.AuthRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class AuthViewMolde(val authRepository: AuthRepository) : BaseViewModel() {

    val regigisterCheckUserLiveData = MutableLiveData<ResponseCheckUser>()
    val registerLiveData = MutableLiveData<ResponseRegister>()
    val loginLiveData = MutableLiveData<ResponseLogin>()
    val addToBookmarkLiveData = MutableLiveData<ResponseAddBookmark>()
    val checkLoginLiveData = MutableLiveData<Boolean>()
    val addToCartLiveData = MutableLiveData<ResponseInsertCommnet>()
    fun checkUser(phone: String) {
        authRepository.checkUser(phone).singleObserver()
            .subscribe(object : DigiShopSingleObserver<ResponseCheckUser>(compositeDisposable) {
                override fun onSuccess(t: ResponseCheckUser) {
                    regigisterCheckUserLiveData.value = t
                }

            })
    }

    fun register(phone: String?, name: String?) {
        authRepository.registerUser(phone!!, name!!)
            .singleObserver()
            .subscribe(object : DigiShopSingleObserver<ResponseRegister>(compositeDisposable) {
                override fun onSuccess(t: ResponseRegister) {
                    registerLiveData.value = t

                }

            })
    }

    fun login(phone: String?) {
        authRepository.login(phone!!)
            .singleObserver()
            .subscribe(object : DigiShopSingleObserver<ResponseLogin>(compositeDisposable) {
                override fun onSuccess(t: ResponseLogin) {
                    loginLiveData.value = t

                }

            })
    }

    fun addToBookmark(productId:Int)
    {

        authRepository.addToBookmark(productId)
            .singleObserver()
            .subscribe(object : DigiShopSingleObserver<ResponseAddBookmark>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseAddBookmark) {

                    addToBookmarkLiveData.value = t
                }

            })
    }
    fun addToCart(productId: Int,colorId:Int,sizeId:Int)
    {
        authRepository.addToCart(productId,colorId,sizeId).singleObserver()
            .subscribe(object :DigiShopSingleObserver<ResponseInsertCommnet>(compositeDisposable)
            {
                override fun onSuccess(t: ResponseInsertCommnet) {
                    addToCartLiveData.value = t
                }

            })
    }

    fun checkLogin() {
        checkLoginLiveData.value = authRepository.checkLogin()
    }

}