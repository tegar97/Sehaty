package com.miftah.sehaty.core.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class CheckResponse(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("userNumber")
	val userNumber: String? = null,

	@field:SerializedName("error")
	val error: String? = null,
)
