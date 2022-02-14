package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseSubCatLevel
import com.example.novinshop_project.feature.home.source.RemoteSubCatLevelDataSource
import com.example.novinshop_project.feature.home.source.SubCatLevelDataSource
import io.reactivex.Single

class SubcatLevelRepositoryImpl(val remoteSubCatLevelDataSource: SubCatLevelDataSource):SubCatLevelRepository {
    override fun getSubcatLevel(subCatId: Int): Single<List<ResponseSubCatLevel>>  = remoteSubCatLevelDataSource.getSubcatLevel(subCatId)
}