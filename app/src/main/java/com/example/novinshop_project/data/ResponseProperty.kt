package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseProperty(

	@field:SerializedName("property")
	val property: List<PropertyItem>,

	@field:SerializedName("main_cetegory")
	val mainCetegory: String
)

data class PropertyItem(

	@field:SerializedName("property")
	val property: String,

	@field:SerializedName("title")
	val title: String
)
