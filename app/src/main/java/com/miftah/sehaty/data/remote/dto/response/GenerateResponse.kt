package com.miftah.sehaty.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class GenerateResponse(

	@field:SerializedName("accessCode")
	val accessCode: String? = null,

	@field:SerializedName("message")
	val message: String,
)
