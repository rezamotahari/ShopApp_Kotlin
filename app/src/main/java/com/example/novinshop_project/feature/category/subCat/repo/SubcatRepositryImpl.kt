package com.example.novinshop_project.feature.category.subCat.repo

import com.example.novinshop_project.data.ResponseBrands
import com.example.novinshop_project.data.ResponsePopularCat
import com.example.novinshop_project.data.ResponseSubCat
import com.example.novinshop_project.feature.category.subCat.source.RemoteSubCatDataSource
import com.example.novinshop_project.feature.category.subCat.source.SubCatDataSource
import io.reactivex.Single

class SubcatRepositryImpl(val remoteSubCatDataSource: SubCatDataSource):SubCatRepository {
    override fun getSubCat(catId: Int): Single<List<ResponseSubCat>>  = remoteSubCatDataSource.getSubCat(catId)
    override fun getBrands(catId: Int): Single<List<ResponseBrands>> = remoteSubCatDataSource.getBrands(catId)
    override fun getPopularCat(catId: Int): Single<List<ResponsePopularCat>>  = remoteSubCatDataSource.getPopularCat(catId)
}