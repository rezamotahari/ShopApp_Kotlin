package com.example.novinshop_project.feature.allAmazing.source

import com.example.novinshop_project.data.ResponseSortAmazing
import io.reactivex.Single

interface SortDataSource {
    fun getAllAmazing(sortId:Int):Single<List<ResponseSortAmazing>>
}