package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseAddress(

	@field:SerializedName("poatal_code")
	val poatalCode: String,

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("id")
	val id: Int
)
