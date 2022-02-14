package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponsePopular
import io.reactivex.Single

interface PopularProductRepository {
    fun getPopularProduct():Single<List<ResponsePopular>>
}