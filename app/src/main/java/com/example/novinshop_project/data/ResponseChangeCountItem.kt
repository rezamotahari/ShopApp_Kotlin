package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseChangeCountItem(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("message")
	val message: String
)
