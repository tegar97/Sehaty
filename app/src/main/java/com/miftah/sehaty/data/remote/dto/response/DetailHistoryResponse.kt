package com.miftah.sehaty.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class DetailHistoryResponse(

	@field:SerializedName("data")
	val data: DataDetailHistory? = null,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataDetailHistory(

	@field:SerializedName("totalCarbs")
	val totalCarbs: Int,

	@field:SerializedName("totalFat")
	val totalFat: Int,

	@field:SerializedName("productPhoto")
	val productPhoto: String,

	@field:SerializedName("sugars")
	val sugars: Int,

	@field:SerializedName("productId")
	val productId: String,

	@field:SerializedName("warnings")
	val warnings: String,

	@field:SerializedName("productName")
	val productName: String,

	@field:SerializedName("sodium")
	val sodium: Int,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("portion100gSize")
	val portion100gSize: String,

	@field:SerializedName("dietaryFiber")
	val dietaryFiber: Int,

	@field:SerializedName("protein")
	val protein: Int,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("portionSize")
	val portionSize: Int,

	@field:SerializedName("energy")
	val energy: Int
)
