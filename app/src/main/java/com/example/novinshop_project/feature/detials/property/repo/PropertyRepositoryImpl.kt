package com.example.novinshop_project.feature.detials.property.repo

import com.example.novinshop_project.data.ResponseProperty
import com.example.novinshop_project.feature.detials.property.source.PropertyDataSource
import com.example.novinshop_project.feature.detials.property.source.RemotePropertyDataSource
import io.reactivex.Single

class PropertyRepositoryImpl(val remotePropertyDataSource: PropertyDataSource):PropertyRepository {
    override fun getProperty(productId: Int): Single<List<ResponseProperty>> = remotePropertyDataSource.getProperty(productId)
}