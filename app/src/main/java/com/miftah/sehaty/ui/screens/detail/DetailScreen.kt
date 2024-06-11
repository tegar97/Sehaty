package com.miftah.sehaty.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miftah.sehaty.R
import com.miftah.sehaty.ui.screens.common.ItemChipWarning
import com.miftah.sehaty.ui.screens.history.components.ChipAndWarning
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    urlImage: String,
    titleItem: String,
    piece: String,
    items: List<ChipAndWarning>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.label_nutrisi_berbahaya),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .wrapContentSize()
        ) {
            Text(
                titleItem,
                modifier = modifier.fillMaxWidth(),
            )
            Text(
                text = piece,
                modifier = modifier.fillMaxWidth()
            )
            items.forEach {
                ItemChipWarning(itemChip = it)
            }
            DetailNutrients()
        }
    }
}

@Composable
fun DetailNutrients(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Nutrition Quality")
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A"
                )
                CircularProgressIndicator(
                    progress = { 0.45f },
                    color = Color.Red,
                    modifier = modifier.size(50.dp)
                )
            }
            Text(
                modifier = modifier.weight(2f),
                text = "Sangat sehat, pilihan terbaik untuk dikonsumsi. Produk ini kaya akan nutrisi yang bermanfaat dan rendah kalori, gula, garam, dan lemak jenuh"
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    SehatyTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                urlImage = "",
                titleItem = "Kacan mede",
                piece = "100g",
                items = listOf(ChipAndWarning(title = "Gula", color = Color.Red))
            )
        }
    }
}