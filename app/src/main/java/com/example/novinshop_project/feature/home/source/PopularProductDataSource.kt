package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.data.ResponsePopular
import io.reactivex.Single

interface PopularProductDataSource {
    fun getPopularProduct(): Single<List<ResponsePopular>>

}