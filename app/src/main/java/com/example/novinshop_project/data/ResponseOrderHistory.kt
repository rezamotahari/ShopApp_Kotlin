package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseOrderHistory(

	@field:SerializedName("ref_id")
	val refId: String,

	@field:SerializedName("images")
	val images: List<ImagesItemHistory>,

	@field:SerializedName("date_time")
	val dateTime: String,

	@field:SerializedName("total_price")
	val totalPrice: String,

	@field:SerializedName("id")
	val id: Int
)

data class ImagesItemHistory(

	@field:SerializedName("url")
	val url: String
)
