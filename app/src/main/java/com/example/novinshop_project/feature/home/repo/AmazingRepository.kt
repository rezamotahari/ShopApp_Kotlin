package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseAmazingProduct
import io.reactivex.Single

interface AmazingRepository {
    fun getAmazingProduct():Single<List<ResponseAmazingProduct>>
}