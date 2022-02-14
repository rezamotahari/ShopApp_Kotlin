package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseCompare(

	@field:SerializedName("score2")
	val score2: Double? = null,

	@field:SerializedName("id2")
	val id2: Int? = null,

	@field:SerializedName("id1")
	val id1: Int? = null,

	@field:SerializedName("property")
	val property: List<PropertyItemProduct?>? = null,

	@field:SerializedName("imageurl2")
	val imageurl2: String? = null,

	@field:SerializedName("imageurl1")
	val imageurl1: String? = null,

	@field:SerializedName("score1")
	val score1: Double? = null,

	@field:SerializedName("name2")
	val name2: String? = null,

	@field:SerializedName("name1")
	val name1: String? = null,

	@field:SerializedName("price1")
	val price1: String? = null,

	@field:SerializedName("price2")
	val price2: String? = null
)

data class PropertyItemProduct(

	@field:SerializedName("values")
	val values: List<ValuesItem>? = null,

	@field:SerializedName("main_cetegory")
	val mainCetegory: String? = null
)

data class ValuesItem(

	@field:SerializedName("property2")
	val property2: String? = null,

	@field:SerializedName("property1")
	val property1: String? = null
)
