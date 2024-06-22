package com.miftah.sehaty.ui.screens.detail.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.White30
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun NutrientCard(
    modifier: Modifier = Modifier,
    nutrientTitle: String,
    description: String,
    nutrientValue: String,
    containerColor: Color,
    titleColor: Color,
    iconVector: ImageVector
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = containerColor,
            contentColor = titleColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.logoSize)
                    .weight(1f)
                    .height(IntrinsicSize.Max)
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimens.logoSize),
                    imageVector = iconVector,
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = nutrientTitle,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 24.sp
                    )
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = description,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Box(
                modifier = Modifier.weight(1f)
            ){
                Button(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { /*TODO*/ },
                    enabled = false,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Color.Yellow,
                        contentColor = Color.White
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier,
                            text = nutrientValue,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 16.sp
                            ),
                            color = titleColor
                        )
                        Text(
                            modifier = Modifier,
                            text = "gram",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 16.sp
                            ),
                            color = titleColor
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun NutrientCardPreview() {
    SehatyTheme {
        NutrientCard(
            modifier = Modifier.fillMaxWidth(),
            nutrientTitle = "Nutrient",
            nutrientValue = "0.3",
            description = "Its good for you",
            containerColor = Color.Cyan.copy(
                alpha = 0.9f
            ),
            iconVector = Icons.Default.LightMode,
            titleColor = Color.Unspecified
        )
    }
}