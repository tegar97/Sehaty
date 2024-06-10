package com.miftah.sehaty.domain.model

import com.miftah.sehaty.data.remote.dto.request.NutritionRequest

data class Nutrition(
    val dietaryFiber: Int,
    val energy: Int,
    val grade: String,
    val kolestrol: Int,
    val nutriScore: Int,
    val portion100g: Portion100g,
    val portionSize: Int,
    val protein: Int,
    val sodium: Int,
    val sugars: Int,
    val totalCarbs: Int,
    val totalFat: Int,
    val warnings: List<String>
)

fun Nutrition.convertToNutritionRequest(): NutritionRequest =
    NutritionRequest(
        dietaryFiber,
        energy,
        grade,
        kolestrol,
        nutriScore,
        portion100g.convertToPortion100gRequest(),
        portionSize,
        protein,
        sodium,
        sugars,
        totalCarbs,
        totalFat,
        warnings
    )
