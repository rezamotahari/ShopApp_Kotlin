package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseScoreItemCommnet(

	@field:SerializedName("score_value")
	var scoreValue: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("score_name")
	val scoreName: String
)
