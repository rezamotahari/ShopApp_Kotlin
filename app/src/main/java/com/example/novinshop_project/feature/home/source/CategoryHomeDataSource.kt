package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.data.ResponseCategoryHome
import io.reactivex.Single

interface CategoryHomeDataSource {
    fun  getCategoryHome(): Single<List<ResponseCategoryHome>>

}