package com.example.novinshop_project.feature.allAmazing.repo

import com.example.novinshop_project.data.ResponseSortAmazing
import io.reactivex.Single

interface SortRepository {
    fun getAllAmazing(sortId:Int):Single<List<ResponseSortAmazing>>
}