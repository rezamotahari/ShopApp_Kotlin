package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.data.ResponseSubCatLevel
import io.reactivex.Single

interface SubCatLevelDataSource {
    fun  getSubcatLevel(subCatId:Int):Single<List<ResponseSubCatLevel>>
}