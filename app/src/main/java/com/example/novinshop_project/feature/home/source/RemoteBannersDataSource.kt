package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseBanners
import io.reactivex.Single

class RemoteBannersDataSource(val apiService: ApiService) : BannerDataSource {
    override fun getBanner(): Single<List<ResponseBanners>>  = apiService.getBanners()
}