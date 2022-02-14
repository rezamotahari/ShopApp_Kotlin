package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseSubCatLevel
import io.reactivex.Single

interface SubCatLevelRepository {
    fun  getSubcatLevel(subCatId:Int):Single<List<ResponseSubCatLevel>>
}