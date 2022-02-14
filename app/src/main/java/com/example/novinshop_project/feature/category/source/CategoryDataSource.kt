package com.example.novinshop_project.feature.category.source

import com.example.novinshop_project.data.ResponseCategory
import io.reactivex.Single

interface CategoryDataSource {

    fun getCategory():Single<List<ResponseCategory>>
}