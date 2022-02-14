package com.example.novinshop_project.feature.profile.infoUser.repo

import com.example.novinshop_project.data.ResponseInfoUser
import io.reactivex.Single

interface InfoRespository {
    fun getInfoUser():Single<ResponseInfoUser>
}