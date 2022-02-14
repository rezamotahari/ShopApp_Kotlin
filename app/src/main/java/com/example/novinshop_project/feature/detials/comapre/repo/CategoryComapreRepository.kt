package com.example.novinshop_project.feature.detials.comapre.repo

import com.example.novinshop_project.data.ResponseCategoryCompare
import io.reactivex.Single

interface CategoryComapreRepository {
    fun getCategoryComapre( productId: Int):Single<List<ResponseCategoryCompare>>
}