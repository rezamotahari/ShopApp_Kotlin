package com.example.novinshop_project.feature.search.repo

import com.example.novinshop_project.data.ResponseSearch
import io.reactivex.Single

interface SearchRepository {
    fun serach(keyword:String):Single<ResponseSearch>
}