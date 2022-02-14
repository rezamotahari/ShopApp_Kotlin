package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.data.ResponseBanners
import io.reactivex.Single

class LocalBannersDataSource:BannerDataSource {
    override fun getBanner(): Single<List<ResponseBanners>> {
        TODO("Not yet implemented")
    }
}