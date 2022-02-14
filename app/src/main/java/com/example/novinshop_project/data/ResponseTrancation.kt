package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseTrancation(

	@field:SerializedName("ref_id")
	val refId: String,

	@field:SerializedName("message")
	val message: String
)
