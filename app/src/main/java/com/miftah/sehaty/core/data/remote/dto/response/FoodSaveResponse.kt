package com.miftah.sehaty.core.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class FoodSaveResponse(

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("error")
    val error: String? = null,

    @field:SerializedName("status")
    val status: String? = null,
)
