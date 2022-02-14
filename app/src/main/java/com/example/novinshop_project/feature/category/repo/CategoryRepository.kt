package com.example.novinshop_project.feature.category.repo

import com.example.novinshop_project.data.ResponseCategory
import io.reactivex.Single

interface CategoryRepository {

    fun getCategory():Single<List<ResponseCategory>>
}