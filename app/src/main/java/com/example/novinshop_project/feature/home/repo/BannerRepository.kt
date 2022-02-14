package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseBanners
import io.reactivex.Single

interface BannerRepository {

    fun getBanner():Single<List<ResponseBanners>>

}