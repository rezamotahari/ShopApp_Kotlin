package com.example.novinshop_project.feature.search.repo

import com.example.novinshop_project.data.ResponseSearch
import com.example.novinshop_project.feature.search.source.RemoteSearchDataSource
import com.example.novinshop_project.feature.search.source.SearchDataSource
import io.reactivex.Single

class SearchRepositoryImpl(val remoteSearchDataSource: SearchDataSource):SearchRepository {
    override fun serach(keyword: String): Single<ResponseSearch>  = remoteSearchDataSource.serach(keyword)
}