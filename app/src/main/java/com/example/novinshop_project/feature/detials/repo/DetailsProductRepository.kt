package com.example.novinshop_project.feature.detials.repo

import com.example.novinshop_project.data.ResponseDetailsProduct
import io.reactivex.Single

interface DetailsProductRepository {
    fun getDetilsProduct(productId: Int):Single<ResponseDetailsProduct>
}