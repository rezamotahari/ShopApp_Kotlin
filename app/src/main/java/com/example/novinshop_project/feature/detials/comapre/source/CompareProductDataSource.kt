package com.example.novinshop_project.feature.detials.comapre.source

import com.example.novinshop_project.data.ResponseCompare
import io.reactivex.Single

interface CompareProductDataSource {
    fun compareProduct(productIdOne:Int , productIdTwo:Int):Single<ResponseCompare>
}