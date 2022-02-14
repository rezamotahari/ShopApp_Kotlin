package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseBannersType
import io.reactivex.Single

class RemoteBannersTypeDataSource(val apiService: ApiService):BannersTypeDataSource {
    override fun getBannersType(): Single<List<ResponseBannersType>>  = apiService.getBannersType()
}