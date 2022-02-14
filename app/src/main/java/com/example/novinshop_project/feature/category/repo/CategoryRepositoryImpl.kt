package com.example.novinshop_project.feature.category.repo

import com.example.novinshop_project.data.ResponseCategory
import com.example.novinshop_project.feature.category.source.CategoryDataSource
import com.example.novinshop_project.feature.category.source.RemoteCategoryDataSource
import io.reactivex.Single

class CategoryRepositoryImpl(val  remoteCategoryDataSource: CategoryDataSource):CategoryRepository {
    override fun getCategory(): Single<List<ResponseCategory>>  = remoteCategoryDataSource.getCategory()
}