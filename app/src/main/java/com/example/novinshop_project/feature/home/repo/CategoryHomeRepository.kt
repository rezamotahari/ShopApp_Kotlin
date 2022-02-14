package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseCategoryHome
import io.reactivex.Single

interface CategoryHomeRepository {
  fun  getCategoryHome():Single<List<ResponseCategoryHome>>
}