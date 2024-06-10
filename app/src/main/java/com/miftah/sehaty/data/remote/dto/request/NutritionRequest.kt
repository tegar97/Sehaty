package com.miftah.sehaty.data.remote.dto.request

data class NutritionRequest(
    val dietaryFiber: Int,
    val energy: Int,
    val grade: String,
    val kolestrol: Int,
    val nutriScore: Int,
    val portion100g: Portion100gRequest,
    val portionSize: Int,
    val protein: Int,
    val sodium: Int,
    val sugars: Int,
    val totalCarbs: Int,
    val totalFat: Int,
    val warnings: List<String>
)