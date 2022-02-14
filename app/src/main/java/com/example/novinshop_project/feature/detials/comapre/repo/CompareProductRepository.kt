package com.example.novinshop_project.feature.detials.comapre.repo

import com.example.novinshop_project.data.ResponseCompare
import io.reactivex.Single

interface CompareProductRepository {
    fun compareProduct(productIdOne:Int , productIdTwo:Int):Single<ResponseCompare>
}