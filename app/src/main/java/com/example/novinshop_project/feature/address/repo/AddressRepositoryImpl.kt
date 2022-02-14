package com.example.novinshop_project.feature.address.repo

import com.example.novinshop_project.data.ResponseAddAddress
import com.example.novinshop_project.data.ResponseAddress
import com.example.novinshop_project.feature.address.source.AddressDataSource
import com.example.novinshop_project.feature.address.source.RemoteAddressDataSource
import com.google.gson.JsonObject
import io.reactivex.Single

class AddressRepositoryImpl(val remoteAddressDataSource: AddressDataSource):AddressRepository {
    override fun getAddress(): Single<List<ResponseAddress>>  = remoteAddressDataSource.getAddress()
    override fun addAddress(address: JsonObject): Single<ResponseAddAddress>  = remoteAddressDataSource.addAddress(address)
}