package com.example.novinshop_project.feature.detials.property.repo

import com.example.novinshop_project.data.ResponseProperty
import com.example.novinshop_project.feature.detials.Details_Product
import io.reactivex.Single

interface PropertyRepository {
    fun getProperty(productId:Int):Single<List<ResponseProperty>>
}