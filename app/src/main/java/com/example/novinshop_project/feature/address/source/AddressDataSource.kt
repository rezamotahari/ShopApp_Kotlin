package com.example.novinshop_project.feature.address.source

import com.example.novinshop_project.data.ResponseAddAddress
import com.example.novinshop_project.data.ResponseAddress
import com.google.gson.JsonObject
import io.reactivex.Single

interface AddressDataSource {
    fun getAddress():Single<List<ResponseAddress>>
    fun addAddress(address: JsonObject):Single<ResponseAddAddress>

}