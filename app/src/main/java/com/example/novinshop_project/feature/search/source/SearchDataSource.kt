package com.example.novinshop_project.feature.search.source

import com.example.novinshop_project.data.ResponseSearch
import io.reactivex.Single

interface SearchDataSource {
    fun serach(keyword:String):Single<ResponseSearch>
}