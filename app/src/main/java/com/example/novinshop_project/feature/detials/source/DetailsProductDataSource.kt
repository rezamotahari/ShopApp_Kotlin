package com.example.novinshop_project.feature.detials.source

import com.example.novinshop_project.data.ResponseDetailsProduct
import io.reactivex.Single

interface DetailsProductDataSource {
    fun getDetilsProduct(productId: Int):Single<ResponseDetailsProduct>
}