package com.miftah.sehaty.domain.model

import com.miftah.sehaty.core.data.remote.dto.response.ScanNutritionResponse

data class FoodAfterScan(
    val grade: String,
    val nutriScore: Int,
    val warnings: List<String>,
    val dietaryFiber: Int,
    val energy: Int,
    val kolestrol: Int,
    val portionSize: Int,
    val protein: Int,
    val sodium: Int,
    val sugars: Int,
    val totalCarbs: Int,
    val totalFat: Int,
    val dietaryFiber100g: Int,
    val energy100g: Int,
    val portionSize100g: String,
    val protein100g: Int,
    val sodium100g: Int,
    val sugars100g: Int,
    val totalCarbs100g: Int,
    val totalFat100g: Int
)

fun ScanNutritionResponse.convertToFoodAfterScan() : FoodAfterScan {
    this.data!!.let {
        return FoodAfterScan(
            warnings = it.warnings,
            grade = it.grade,
            nutriScore = it.nutriScore,
            dietaryFiber = it.nutrition.dietaryFiber,
            energy = it.nutrition.energy,
            totalFat = it.nutrition.totalFat,
            portionSize = it.nutrition.portionSize,
            totalCarbs = it.nutrition.totalCarbs,
            sodium = it.nutrition.sodium,
            sugars = it.nutrition.sugars,
            kolestrol = it.nutrition.kolestrol,
            protein = it.nutrition.protein,
            dietaryFiber100g = it.portion100g.dietaryFiber,
            totalCarbs100g = it.nutrition.totalCarbs,
            totalFat100g = it.portion100g.totalFat,
            energy100g = it.portion100g.energy,
            sodium100g = it.portion100g.sodium,
            sugars100g = it.portion100g.sugars,
            protein100g = it.portion100g.protein,
            portionSize100g = it.portion100g.portionSize
        )
    }
}

