package com.miftah.sehaty.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.miftah.sehaty.domain.model.HistoryScanned
import com.miftah.sehaty.ui.screens.common.ChipAndWarning
import com.miftah.sehaty.ui.screens.common.GradeNutrient
import com.miftah.sehaty.ui.screens.common.ItemChipWarning
import com.miftah.sehaty.ui.screens.detail.components.DetailNutrient
import com.miftah.sehaty.ui.theme.Grey70
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    urlImage: String,
    titleItem: String,
    piece: String,
    itemsChip: List<ChipAndWarning>,
    historyScanned: HistoryScanned
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        ProductImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), // Membuat kotak persegi (1:1 aspect ratio)
            urlImage = urlImage
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            NutrientDetailSection(
                modifier = Modifier,
                titleItem = titleItem,
                piece = piece,
                items = itemsChip
            )
            NutrientsSummarySection(
                modifier = modifier.padding(top = 24.dp)
            )
            NutrientsSection(
                modifier = modifier.padding(top = 24.dp),
                historyScanned = historyScanned
            )
        }
    }
}

@Composable
fun NutrientsSection(modifier: Modifier, historyScanned: HistoryScanned) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailNutrient(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp).weight(1f),
                nutrient = "Protein",
                size = historyScanned.protein.toString()
            )
            DetailNutrient(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp).weight(1f),
                nutrient = "Lemak",
                size = historyScanned.totalFat.toString()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailNutrient(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp).weight(1f),
                nutrient = "Karbo",
                size = historyScanned.totalCarbs.toString()
            )
            DetailNutrient(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp).weight(1f),
                nutrient = "Gula",
                size = historyScanned.sugars.toString()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DetailNutrient(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp).weight(1f),
                nutrient = "Serat",
                size = historyScanned.dietaryFiber.toString()
            )
            DetailNutrient(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp).weight(1f),
                nutrient = "Garam",
                size = historyScanned.sodium.toString()
            )
        }
    }
}

@Composable
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
/*            NutrientsSection(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                itemsNutrient = dummyNutrients(6)
            )*/
        }
    }
}

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
            modifier = Modifier
                .fillMaxSize() // Mengisi seluruh kotak
                .clip(RoundedCornerShape(16.dp)) // Rounded corner opsional
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
        modifier = modifier
    ) {
        Text(
            titleItem,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = piece,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = Grey70
            )
        )
        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items.forEach {
                ItemChipWarning(
                    modifier = modifier.padding(end = 8.dp),
                    itemChip = it
                )
            }
        }
    }
}

@Composable
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
}


/*fun LazyGridScope.sublistDetailNutrient(itemsNutrient: List<Nutrition>) {
    items(itemsNutrient.size) {
        DetailNutrient(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        )
    }
}*/

@Composable
fun NutrientsSummarySection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Nutrition Quality",
            style = MaterialTheme.typography.labelLarge
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradeNutrient(
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 50,
                indicatorSize = 100,
                percentage = 0.6f,
                strokeWidth = 8,
                indicatorColor = Color.Red
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center,
                text = "Sangat sehat, pilihan terbaik untuk dikonsumsi. Produk ini kaya akan nutrisi yang bermanfaat dan rendah kalori, gula, garam, dan lemak jenuh"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    SehatyTheme {
        /*Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                urlImage = "https://picsum.photos/id/237/200/300",
                titleItem = "Kacan mede",
                piece = "100g",
                historyScanned = dummyNutrient(1),
                itemsChip = listOf(
                    ChipAndWarning(
                        title = "Gula",
                        containerColor = Color.Red,
                        titleColor = Color.White
                    )
                )
            )
        }*/
    }
}