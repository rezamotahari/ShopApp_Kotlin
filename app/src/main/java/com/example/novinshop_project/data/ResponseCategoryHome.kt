package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseCategoryHome(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)
