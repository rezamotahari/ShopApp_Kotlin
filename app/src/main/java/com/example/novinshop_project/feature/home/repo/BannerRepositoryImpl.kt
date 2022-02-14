package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseBanners
import com.example.novinshop_project.feature.home.source.BannerDataSource
import com.example.novinshop_project.feature.home.source.LocalBannersDataSource
import io.reactivex.Single

class BannerRepositoryImpl(val remoteBannersDataSource: BannerDataSource,
                           val localBannersDataSource: LocalBannersDataSource):BannerRepository {
    override fun getBanner(): Single<List<ResponseBanners>>  = remoteBannersDataSource.getBanner()
}