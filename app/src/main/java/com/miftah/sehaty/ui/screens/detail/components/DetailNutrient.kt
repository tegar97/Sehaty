package com.miftah.sehaty.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.ui.screens.common.GradeNutrient
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun DetailNutrient(
    modifier: Modifier = Modifier,
    size: String,
    portion: String = "/g",
    nutrient: String,
    vector: ImageVector,
    iconColor: Color,
    containerColor : Color,
    textColor: Color
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(containerColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconDetail(
            icon = vector,
            color = iconColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column{
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = size,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 24.sp,
                        color = textColor
                    ),
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = portion,
                    color = textColor
                )
            }
            Text(
                text = nutrient,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = textColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
fun IconDetail(
    modifier: Modifier = Modifier,
    icon : ImageVector,
    color : Color
) {
    GradeNutrient(
        fontSize = 25,
        indicatorSize = 50,
        percentage = 0.6f,
        strokeWidth = 5,
        indicatorColor = Color.Red,
        score = "A"
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailCardPreview() {
    SehatyTheme {
        DetailNutrient(
            size = "0.3",
            nutrient = "Energy",
            iconColor = MaterialTheme.colorScheme.onPrimary,
            vector = Icons.Default.ElectricBolt,
            containerColor = Color.Yellow,
            textColor = Color.Red
        )
    }
}