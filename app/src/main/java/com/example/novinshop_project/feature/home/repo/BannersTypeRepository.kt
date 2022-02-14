package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseBannersType
import io.reactivex.Single

interface BannersTypeRepository {
  fun  getBannersType(): Single<List<ResponseBannersType>>
}