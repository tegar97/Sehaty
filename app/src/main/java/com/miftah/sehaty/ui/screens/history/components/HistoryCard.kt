package com.miftah.sehaty.ui.screens.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.miftah.sehaty.R
import com.miftah.sehaty.ui.screens.common.ChipAndWarning
import com.miftah.sehaty.ui.screens.common.GradeNutrient
import com.miftah.sehaty.ui.screens.common.ItemChipWarning
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.White30
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun HistoryCard(
    modifier: Modifier = Modifier,
    urlImage: String,
    itemName: String,
    itemsChip: List<ChipAndWarning>
) {
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.elevatedCardColors().copy(
            containerColor = White30
        )
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (image, title, time) = createRefs()

            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .size(100.dp)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                model = urlImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(title) {
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                    }
            ) {
                Row(
                    modifier = Modifier.padding(top = MaterialTheme.dimens.small1),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier,
                        text = itemName,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "100g",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Row(
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    itemsChip.forEach {
                        ItemChipWarning(
                            modifier = Modifier.padding(end = 8.dp),
                            itemChip = it
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .constrainAs(time) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "00:00:00:00:00",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Light
                    )
                )
                Icon(
                    imageVector = Icons.Default.AccessTime, contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun HistoryCardPrev() {
    SehatyTheme {
        HistoryCard(
            urlImage = "https://tabris.com/wp-content/uploads/2021/06/jetpack-compose-icon_RGB.png",
            itemName = "Biscuittttttttttttttt",
            itemsChip = listOf(
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
}