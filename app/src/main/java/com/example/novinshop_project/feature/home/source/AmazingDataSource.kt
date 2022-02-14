package com.example.novinshop_project.feature.home.source

import com.example.novinshop_project.data.ResponseAmazingProduct
import io.reactivex.Single

interface AmazingDataSource {
    fun getAmazingProduct():Single<List<ResponseAmazingProduct>>
}