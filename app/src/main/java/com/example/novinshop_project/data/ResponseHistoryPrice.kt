package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseHistoryPrice(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("id")
	val id: String
)
