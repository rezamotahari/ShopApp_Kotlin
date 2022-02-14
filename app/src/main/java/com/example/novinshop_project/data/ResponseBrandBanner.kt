package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseBrandBanner(

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("banner")
	val banner: String
)
