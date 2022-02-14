package com.example.novinshop_project.feature.address.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseAddAddress
import com.example.novinshop_project.data.ResponseAddress
import com.google.gson.JsonObject
import io.reactivex.Single

class RemoteAddressDataSource(val apiService: ApiService):AddressDataSource {
    override fun getAddress(): Single<List<ResponseAddress>>  = apiService.getAddress()
    override fun addAddress(address: JsonObject): Single<ResponseAddAddress>  = apiService.addAddress(address)
}