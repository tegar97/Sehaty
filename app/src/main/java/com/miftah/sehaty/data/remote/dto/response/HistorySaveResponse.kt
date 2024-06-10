package com.miftah.sehaty.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class HistorySaveResponse(

	@field:SerializedName("data")
	val data: DataHistoryResponse? = null,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: String? = null,
)

data class Portion100gHistoryResponse(

	@field:SerializedName("portionSize")
	val portionSize: String
)

data class DataHistoryResponse(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("nutrition")
	val nutrition: NutritionHistoryResponse,

	@field:SerializedName("productId")
	val productId: String,

	@field:SerializedName("warnings")
	val warnings: List<Any>,

	@field:SerializedName("__v")
	val v: Int,

	@field:SerializedName("portion100g")
	val portion100g: Portion100gHistoryResponse,

	@field:SerializedName("whatsappToken")
	val whatsappToken: String,

	@field:SerializedName("_id")
	val id: String
)

data class NutritionHistoryResponse(

	@field:SerializedName("totalCarbs")
	val totalCarbs: Int,

	@field:SerializedName("sodium")
	val sodium: Int,

	@field:SerializedName("totalFat")
	val totalFat: Int,

	@field:SerializedName("sugars")
	val sugars: Int,

	@field:SerializedName("dietaryFiber")
	val dietaryFiber: Int,

	@field:SerializedName("protein")
	val protein: Int,

	@field:SerializedName("portionSize")
	val portionSize: Int,

	@field:SerializedName("energy")
	val energy: Int
)
