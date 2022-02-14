package com.example.novinshop_project.data

import com.google.gson.annotations.SerializedName

data class ResponseScoreInsert(

	@field:SerializedName("score")
	val score: List<ScoreItems>,

	@field:SerializedName("status")
	val status: String
)

data class ScoreItems(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("score_name")
	val scoreName: String,

	@field:SerializedName("value")
	var value: Int
)
