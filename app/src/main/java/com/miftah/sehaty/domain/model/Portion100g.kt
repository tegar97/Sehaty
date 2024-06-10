package com.miftah.sehaty.domain.model

import com.google.gson.annotations.SerializedName
import com.miftah.sehaty.data.remote.dto.Portion100gRequest

data class Portion100g(
    val dietaryFiber: Double,
    val energy: Double,
    val portionSize: String,
    val protein: Double,
    val sodium: Double,
    val sugars: Double,
    val totalCarbs: Int,
    val totalFat: Double
)

fun Portion100g.convertToPortion100gRequest() : Portion100gRequest =
    Portion100gRequest(
        dietaryFiber,
        energy,
        portionSize,
        protein,
        sodium,
        sugars,
        totalCarbs,
        totalFat
    )

