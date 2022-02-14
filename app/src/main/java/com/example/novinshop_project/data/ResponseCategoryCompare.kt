package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseCategoryCompare(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("off_price")
	val offPrice: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("off_percent")
	val offPercent: String
)
