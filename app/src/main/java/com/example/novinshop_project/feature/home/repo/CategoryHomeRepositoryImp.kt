package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseCategoryHome
import com.example.novinshop_project.feature.home.source.CategoryHomeDataSource
import io.reactivex.Single

class CategoryHomeRepositoryImp(val  remoteCategoryHomeDataSource: CategoryHomeDataSource):CategoryHomeRepository {
    override fun getCategoryHome(): Single<List<ResponseCategoryHome>>  = remoteCategoryHomeDataSource.getCategoryHome()
}