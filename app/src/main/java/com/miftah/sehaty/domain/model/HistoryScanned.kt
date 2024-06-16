package com.miftah.sehaty.domain.model

import com.miftah.sehaty.core.data.local.entity.HistoryScannedEntity
import com.miftah.sehaty.core.data.remote.dto.response.GetHistoryResponse

data class HistoryScanned(
    val createdAt: String,
    val dietaryFiber: Int,
    val energy: Int,
    val grade: String,
    val nutriScore: Int,
    val portion100gDietaryFiber: Int,
    val portion100gEnergy: Int,
    val portion100gProtein: Int,
    val portion100gSize: String,
    val portion100gSodium: Int,
    val portion100gSugars: Int,
    val portion100gTotalCarbs: Int,
    val portion100gTotalFat: Int,
    val portionSize: Int,
    val productName: String,
    val productPhoto: String,
    val protein: Int,
    val sodium: Int,
    val sugars: Int,
    val totalCarbs: Int,
    val totalFat: Int,
    val warnings: List<String>
)

fun GetHistoryResponse.getHistoryConvertToScanItem(): List<HistoryScanned> {
    val result: MutableList<HistoryScanned> = arrayListOf()
    this.data.forEach {
        result.add(
            HistoryScanned(
                totalCarbs = it.totalCarbs,
                totalFat = it.totalFat,
                productPhoto = it.productPhoto,
                sugars = it.sugars,
                warnings = listOf(it.warnings),
                productName = it.productName,
                sodium = it.sodium,
                createdAt = it.createdAt,
                portion100gSize = it.portion100gSize,
                dietaryFiber = it.dietaryFiber,
                protein = it.protein,
                portionSize = it.portionSize,
                energy = it.energy,
                portion100gDietaryFiber = it.portion100gDietaryFiber,
                portion100gEnergy = it.portion100gEnergy,
                portion100gProtein = it.portion100gProtein,
                portion100gSodium = it.portion100gSodium,
                portion100gSugars = it.portion100gSugars,
                portion100gTotalCarbs = it.portion100gTotalCarbs,
                portion100gTotalFat = it.portion100gTotalFat,
                grade = it.grade,
                nutriScore = it.nutriScore
            )
        )
    }
    return result
}

fun HistoryScannedEntity.convertToHistoryScanned() =
    HistoryScanned(
        totalCarbs = totalCarbs,
        totalFat = totalFat,
        productPhoto = productPhoto,
        sugars = sugars,
        warnings = listOf(warnings),
        productName = productName,
        sodium = sodium,
        createdAt = createdAt,
        portion100gSize = portion100gSize,
        dietaryFiber = dietaryFiber,
        protein = protein,
        portionSize = portionSize,
        energy = energy,
        portion100gDietaryFiber = portion100gDietaryFiber,
        portion100gEnergy = portion100gEnergy,
        portion100gProtein = portion100gProtein,
        portion100gSodium = portion100gSodium,
        portion100gSugars = portion100gSugars,
        portion100gTotalCarbs = portion100gTotalCarbs,
        portion100gTotalFat = portion100gTotalFat,
        grade = grade,
        nutriScore = nutriScore
    )


/*fun FoodAfterScan.convertToHistoryScanned(productPhoto: String) =
    HistoryScanned(
        sugars = sugars,
        sodium = sodium,
        productPhoto = productPhoto,
        totalCarbs = totalCarbs,
        createdAt =
    )*/

/*fun ScanNutritionResponse.scanNutritionToScanItem(): ScanItem =
    this.data!!.let {
        return ScanItem(
            totalCarbs = it.nutrition.totalCarbs,
            totalFat = it.nutrition.totalFat,
            dietaryFiber = it.nutrition.dietaryFiber,
            portionSize = it.nutrition.portionSize,
            protein = it.nutrition.protein,
            sodium = it.nutrition.sodium,
            sugars = it.nutrition.sugars,
            energy = it.nutrition.energy,
            nutriScore = it.nutriScore,
            grade = it.grade,
            portion100gSize = it.portion100g.portionSize,
            warnings = it.warnings,
            portion100gDietaryFiber = it.portion100g.dietaryFiber,
            portion100gEnergy = it.portion100g.energy,
            portion100gProtein = it.portion100g.protein,
            portion100gSodium = it.portion100g.sodium,
            portion100gSugars = it.portion100g.sugars,
            portion100gTotalCarbs = it.portion100g.totalCarbs,
            portion100gTotalFat = it.portion100g.totalFat,
            productName = it.
        )
    }*/

/*fun DetailHistoryResponse.detailHistoryToScanItem(): ScanItem =
    this.data!!.let {
        return ScanItem(
            totalCarbs = it.totalCarbs,
            totalFat = it.totalFat,
            dietaryFiber = it.dietaryFiber,
            portionSize = it.portionSize,
            protein = it.protein,
            sodium = it.sodium,
            sugars = it.sugars,
            energy = it.energy,
            portion100gSize = it.portion100gSize,
            warnings = listOf(it.warnings),
            productPhoto = it.productPhoto,
        )
    }*/

/*
fun HistorySaveResponse.historySaveToScanItem(): ScanItem =
    this.data!!.let {
        return ScanItem(
            totalCarbs = it.nutrition.totalCarbs,
            totalFat = it.nutrition.totalFat,
            dietaryFiber = it.nutrition.dietaryFiber,
            portionSize = it.nutrition.portionSize,
            protein = it.nutrition.protein,
            sodium = it.nutrition.sodium,
            sugars = it.nutrition.sugars,
            energy = it.nutrition.energy,
            portion100gSize = it.portion100g.portionSize,
            warnings = it.warnings,
            createdAt = it.createdAt,
        )
    }
*/
