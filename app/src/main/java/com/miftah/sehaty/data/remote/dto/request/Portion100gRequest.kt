package com.miftah.sehaty.data.remote.dto.request

data class Portion100gRequest(
    val dietaryFiber: Double,
    val energy: Double,
    val portionSize: String,
    val protein: Double,
    val sodium: Double,
    val sugars: Double,
    val totalCarbs: Int,
    val totalFat: Double
)