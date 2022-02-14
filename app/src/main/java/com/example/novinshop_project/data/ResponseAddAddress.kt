package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseAddAddress(

	@field:SerializedName("number")
	val number: String,

	@field:SerializedName("unit")
	val unit: String,

	@field:SerializedName("poatal_code")
	val poatalCode: String,

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("State")
	val state: String,

	@field:SerializedName("lang")
	val lang: String,

	@field:SerializedName("lat")
	val lat: String
)
