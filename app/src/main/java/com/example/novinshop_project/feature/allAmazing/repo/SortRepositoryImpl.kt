package com.example.novinshop_project.feature.allAmazing.repo

import com.example.novinshop_project.data.ResponseSortAmazing
import com.example.novinshop_project.feature.allAmazing.source.RemoteSortDataSource
import com.example.novinshop_project.feature.allAmazing.source.SortDataSource
import io.reactivex.Single

class SortRepositoryImpl(val remoteSortDataSource: SortDataSource):SortRepository {
    override fun getAllAmazing(sortId: Int): Single<List<ResponseSortAmazing>>  = remoteSortDataSource.getAllAmazing(sortId)
}