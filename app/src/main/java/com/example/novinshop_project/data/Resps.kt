package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class Resps(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("negative")
	val negative: String,

	@field:SerializedName("name_family")
	val nameFamily: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("positive")
	val positive: String,

	@field:SerializedName("Advice")
	val advice: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("value")
	val value: String,

	@field:SerializedName("content")
	val content: String
)
