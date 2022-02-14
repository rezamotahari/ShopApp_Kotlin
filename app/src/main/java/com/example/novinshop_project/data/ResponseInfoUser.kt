package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseInfoUser(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("name_family")
	val nameFamily: String? = null,

	@field:SerializedName("wallet")
	val wallet: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("proccesing")
	val proccesing: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
