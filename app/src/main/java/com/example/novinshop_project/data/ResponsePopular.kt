package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponsePopular(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int
)
