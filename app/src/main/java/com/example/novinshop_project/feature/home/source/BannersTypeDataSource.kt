package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.data.ResponseBannersType
import io.reactivex.Single

interface BannersTypeDataSource {
  fun  getBannersType(): Single<List<ResponseBannersType>>
}