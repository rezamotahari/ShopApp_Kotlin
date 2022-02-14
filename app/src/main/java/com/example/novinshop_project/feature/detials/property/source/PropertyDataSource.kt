package com.example.novinshop_project.feature.detials.property.source

import com.example.novinshop_project.data.ResponseProperty
import com.example.novinshop_project.feature.detials.Details_Product
import io.reactivex.Single

interface PropertyDataSource {
    fun getProperty(productId:Int):Single<List<ResponseProperty>>
}