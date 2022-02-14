package com.example.novinshop_project.feature.profile.infoUser.source

import com.example.novinshop_project.data.ResponseInfoUser
import io.reactivex.Single

interface InfoDataSource {
    fun getInfoUser():Single<ResponseInfoUser>
}