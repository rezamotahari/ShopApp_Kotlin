package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseBrands(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
