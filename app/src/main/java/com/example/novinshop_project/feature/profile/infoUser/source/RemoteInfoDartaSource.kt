package com.example.novinshop_project.feature.profile.infoUser.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseInfoUser
import io.reactivex.Single

class RemoteInfoDartaSource(val apiService: ApiService):InfoDataSource {
    override fun getInfoUser(): Single<ResponseInfoUser>  = apiService.getInfoUser()
}