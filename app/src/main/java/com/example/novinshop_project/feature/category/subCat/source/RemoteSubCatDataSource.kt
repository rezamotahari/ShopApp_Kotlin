package com.example.novinshop_project.feature.category.subCat.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseBrands
import com.example.novinshop_project.data.ResponsePopularCat
import com.example.novinshop_project.data.ResponseSubCat
import io.reactivex.Single

class RemoteSubCatDataSource(val apiService: ApiService):SubCatDataSource {
    override fun getSubCat(catId: Int): Single<List<ResponseSubCat>>  = apiService.getSubCat(catId)
    override fun getBrands(catId: Int): Single<List<ResponseBrands>>  = apiService.getBrands(catId)
    override fun getPopularCat(catId: Int): Single<List<ResponsePopularCat>> = apiService.getPopularCat(catId)
}