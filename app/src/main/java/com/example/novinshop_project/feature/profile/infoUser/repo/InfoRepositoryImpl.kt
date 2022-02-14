package com.example.novinshop_project.feature.profile.infoUser.repo

import com.example.novinshop_project.data.ResponseInfoUser
import com.example.novinshop_project.feature.profile.infoUser.source.InfoDataSource
import io.reactivex.Single

class InfoRepositoryImpl(val remoteInfoDartaSource: InfoDataSource):InfoRespository {
    override fun getInfoUser(): Single<ResponseInfoUser>  = remoteInfoDartaSource.getInfoUser()
}