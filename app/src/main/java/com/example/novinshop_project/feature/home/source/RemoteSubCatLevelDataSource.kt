package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseSubCatLevel
import io.reactivex.Single

class RemoteSubCatLevelDataSource(val apiService: ApiService):SubCatLevelDataSource {
    override fun getSubcatLevel(subCatId: Int): Single<List<ResponseSubCatLevel>> = apiService.getSubCatLevel(subCatId)
}