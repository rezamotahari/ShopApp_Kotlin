package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseShowScore(

	@field:SerializedName("score")
	val score: List<ScoreItem>,

	@field:SerializedName("status")
	val status: String
)

data class ScoreItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("score_name")
	val scoreName: String,

	@field:SerializedName("value")
	val value: Int
)
