package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseAddBookmark(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
