package com.example.novinshop_project.feature.detials.comapre.repo

import com.example.novinshop_project.data.ResponseCategoryCompare
import com.example.novinshop_project.feature.detials.comapre.source.CategoryComapreDataSource
import com.example.novinshop_project.feature.detials.comapre.source.RemoteCategoryComapreDataSource
import io.reactivex.Single

class CategoryComapreRepositoryImpl(val remoteCategoryComapreDataSource: CategoryComapreDataSource):CategoryComapreRepository {
    override fun getCategoryComapre(productId: Int): Single<List<ResponseCategoryCompare>>  =
        remoteCategoryComapreDataSource.getCategoryComapre(productId)
}