package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

	@field:SerializedName("subcat")
	val subcat: List<SubcatItem>,

	@field:SerializedName("title")
	val title: String
)

data class SubcatItem(

	@field:SerializedName("sub_title")
	val subTitle: String,

	@field:SerializedName("sub_id")
	val subId: String,

	@field:SerializedName("sub_image")
	val subImage: String
)
