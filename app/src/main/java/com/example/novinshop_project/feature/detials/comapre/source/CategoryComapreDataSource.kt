package com.example.novinshop_project.feature.detials.comapre.source

import com.example.novinshop_project.data.ResponseCategoryCompare
import io.reactivex.Single

interface CategoryComapreDataSource {
    fun getCategoryComapre( productId: Int):Single<List<ResponseCategoryCompare>>
}