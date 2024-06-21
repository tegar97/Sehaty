package com.miftah.sehaty.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.miftah.sehaty.core.dummyFoodAfterScan
import com.miftah.sehaty.domain.model.FoodAfterScan
import com.miftah.sehaty.domain.model.HistoryScanned
import com.miftah.sehaty.ui.screens.common.ButtonPrimary
import com.miftah.sehaty.ui.screens.common.ChipAndWarning
import com.miftah.sehaty.ui.screens.common.GradeNutrient
import com.miftah.sehaty.ui.screens.common.ItemChipWarning
import com.miftah.sehaty.ui.screens.detail.components.DetailNutrient
import com.miftah.sehaty.ui.theme.Grey70
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.White50
import com.miftah.sehaty.utils.UiState

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    onEvent: (DetailEvent) -> Unit,
    backToHistory: () -> Unit
) {
    Scaffold(
        bottomBar = {
            ButtonPrimary(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp),
                title = if (state.isActive) "Save to Cloud" else "Save To Local",
                textColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                if (state.isActive) {
                    onEvent(DetailEvent.SaveToCloud)
                } else {
                    onEvent(DetailEvent.SaveToLocal)
                }
            }
        }
    ) { innerPadding ->
        state.foodAfterScan?.let {
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    ProductImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f), // Membuat kotak persegi (2:1 aspect ratio)
                        urlImage = it.productPhoto,
                    )
                    NutrientDetailSection(
                        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainerLow),
                        titleItem = it.productName ?: "",
                        piece = it.portionSize.toString(),
                        items = it.warnings.map { item ->
                            ChipAndWarning(
                                title = item,
                                containerColor = Color.Red,
                                titleColor = Color.White
                            )
                        }
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainerLow)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "Nutrition Quality",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 18.sp
                            )
                        )
                        NutrientsSummarySection(
                            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainerLow),
                            scoreResult = it.grade
                        )
                        Text(
                            modifier = Modifier.padding(top = 24.dp, end = 16.dp, start = 16.dp),
                            text = "Nutrition Summary",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 18.sp
                            ),
                        )
                        NutrientsSection(
                            modifier = Modifier.padding(top = 8.dp),
                            foodAfterScan = it
                        )
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                    }
                }
                state.saveFoodAfterScan?.collectAsState(initial = null)?.value.let {
                    when (it) {
                        is UiState.Error -> {
                            AlertDialog(
                                dismissButton = {
                                    TextButton(
                                        onClick = { },
                                        modifier = Modifier.padding(8.dp),
                                    ) {
                                        Text("Dismiss")
                                    }
                                },
                                onDismissRequest = {

                                },
                                confirmButton = {
                                    TextButton(
                                        onClick = { },
                                        modifier = Modifier.padding(8.dp),
                                    ) {
                                        Text("Confirm")
                                    }
                                },
                                title = { Text(text = "Err") },
                                text = { Text(text = it.error) }
                            )
                        }

                        UiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }

                        is UiState.Success -> {
                            backToHistory()
                        }

                        null -> {}
                    }
                }
            }

        }

    }
}

@Composable
fun NutrientsSection(modifier: Modifier, foodAfterScan: FoodAfterScan) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailNutrient(
                modifier = Modifier
                    .weight(1f),
                nutrient = "Protein",
                size = foodAfterScan.protein.toString(),
                iconColor = MaterialTheme.colorScheme.onPrimary,
                vector = Icons.Default.ElectricBolt
            )
            Spacer(modifier = Modifier.width(16.dp))
            DetailNutrient(
                modifier = Modifier
                    .weight(1f),
                nutrient = "Lemak",
                size = foodAfterScan.totalFat.toString(),
                iconColor = MaterialTheme.colorScheme.onPrimary,
                vector = Icons.Default.ElectricBolt
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailNutrient(
                modifier = Modifier
                    .weight(1f),
                nutrient = "Karbo",
                size = foodAfterScan.totalCarbs.toString(),
                iconColor = MaterialTheme.colorScheme.onPrimary,
                vector = Icons.Default.ElectricBolt
            )
            Spacer(modifier = Modifier.width(16.dp))
            DetailNutrient(
                modifier = Modifier
                    .weight(1f),
                nutrient = "Gula",
                size = foodAfterScan.sugars.toString(),
                iconColor = MaterialTheme.colorScheme.onPrimary,
                vector = Icons.Default.ElectricBolt
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailNutrient(
                modifier = Modifier
                    .padding()
                    .weight(1f),
                nutrient = "Serat",
                size = foodAfterScan.dietaryFiber.toString(),
                iconColor = MaterialTheme.colorScheme.onPrimary,
                vector = Icons.Default.ElectricBolt
            )
            Spacer(modifier = Modifier.width(16.dp))
            DetailNutrient(
                modifier = Modifier
                    .weight(1f),
                nutrient = "Garam",
                size = foodAfterScan.sodium.toString(),
                iconColor = MaterialTheme.colorScheme.onPrimary,
                vector = Icons.Default.ElectricBolt
            )
        }
    }
}

/*@Composable
fun DetailScreenTesting(
    modifier: Modifier = Modifier,
    urlImage: String,
    titleItem: String,
    piece: String,
    items: List<ChipAndWarning>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            ProductImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.5f), // Membuat kotak persegi (1:1 aspect ratio)
                urlImage = urlImage
            )
        }
        item {
            NutrientDetailSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                titleItem = "Kacan mede",
                piece = "100g",
                items = listOf(
                    ChipAndWarning(
                        title = "Gula",
                        containerColor = Color.Red,
                        titleColor = Color.White
                    ),
                    ChipAndWarning(
                        title = "Gula",
                        containerColor = Color.Red,
                        titleColor = Color.White
                    )
                )
            )
        }
        item {
            NutrientsSummarySection(
                modifier = modifier.padding(top = 24.dp)
            )
        }

        item {
*//*            NutrientsSection(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                itemsNutrient = dummyNutrients(6)
            )*//*
        }
    }
}*/

@Composable
fun ProductImage(modifier: Modifier = Modifier, urlImage: String) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(urlImage)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize() // Mengisi seluruh kotak
        )
    }
}

@Composable
fun NutrientDetailSection(
    modifier: Modifier = Modifier,
    titleItem: String,
    piece: String,
    items: List<ChipAndWarning>
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            titleItem,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = piece + "g",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Grey70,
                fontWeight = FontWeight.Light
            )
        )
        Row(
            modifier = Modifier.padding(end = 8.dp, top = 8.dp)
        ) {
            items.forEach {
                ItemChipWarning(
                    modifier = Modifier,
                    itemChip = it
                )
            }
        }
    }
}

/*@Composable
fun NutrientsSectionLazy(
    modifier: Modifier = Modifier,
    historyScanned: List<HistoryScanned>
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(count = 2)
    ) {
        item {
            Text(
                "Nutrition Detail",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.labelLarge
            )
        }
        item {

        }
//        if (itemsNutrient.isNotEmpty()) sublistDetailNutrient(itemsNutrient)
    }
}*/


/*fun LazyGridScope.sublistDetailNutrient(itemsNutrient: List<Nutrition>) {
    items(itemsNutrient.size) {
        DetailNutrient(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        )
    }
}*/

@Composable
fun NutrientsSummarySection(
    modifier: Modifier = Modifier,
    scoreResult: String
) {
    val result = scoreDesc(scoreResult)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(White50)
            .clip(RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradeNutrient(
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 50,
                indicatorSize = 100,
                percentage = 0.6f,
                strokeWidth = 8,
                indicatorColor = result.color,
                score = result.grade
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                textAlign = TextAlign.Center,
                text = result.desc,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

fun scoreDesc(result: String): ScoreDescItem {
    return when (result) {
        "A" -> ScoreDescItem(
            desc = "Sangat sehat, pilihan terbaik untuk dikonsumsi. Produk ini kaya akan nutrisi yang bermanfaat dan rendah kalori, gula, garam, dan lemak jenuh.",
            color = Color.Green,
            grade = "A"
        )

        "B" -> ScoreDescItem(
            desc = "Sehat, pilihan baik untuk dikonsumsi. Produk ini memiliki sedikit lebih banyak kalori, gula, garam, atau lemak jenuh tetapi masih merupakan pilihan yang sehat.",
            color = Color.Green,
            grade = "B"
        )

        "C" -> ScoreDescItem(
            desc = "Cukup sehat, boleh dikonsumsi secara moderat. Produk ini memiliki keseimbangan antara nutrisi yang bermanfaat dan yang kurang diinginkan.",
            color = Color.Yellow,
            grade = "C"
        )

        "D" -> ScoreDescItem(
            desc = "Kurang sehat, batasi konsumsi. Produk ini lebih tinggi kalori, gula, garam, atau lemak jenuh dan sebaiknya dikonsumsi secara terbatas.",
            color = Color.Red,
            grade = "D"
        )

        "E" -> ScoreDescItem(
            desc = "Tidak sehat, hindari konsumsi jika memungkinkan. Produk ini sangat tinggi kalori, gula, garam, atau lemak jenuh dan sebaiknya dikonsumsi sesedikit mungkin.",
            color = Color.Red,
            grade = "E"
        )

        else -> ScoreDescItem(
            desc = "Unknown",
            color = Color.Gray,
            grade = "?"
        )
    }
}

data class ScoreDescItem(
    val desc: String,
    val color: Color,
    val grade: String
)

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    SehatyTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                onEvent = {

                },
                state = DetailState(
                    foodAfterScan = dummyFoodAfterScan()
                )
            ) {

            }
        }
    }
}