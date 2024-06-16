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
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.ui.theme.Grey40
import com.miftah.sehaty.ui.theme.Grey70
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun DetailNutrient(
    modifier: Modifier = Modifier,
    size: String,
    portion: String = "/g",
    nutrient: String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Blue)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconDetail()
        Spacer(modifier = Modifier.width(16.dp))
        Column{
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    size,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 24.sp
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = portion
                )
            }
            Text(
                text = nutrient,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = Grey70
                )
            )
        }
    }
}

@Composable
fun IconDetail(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(MaterialTheme.dimens.medium3)
            .clip(CircleShape)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = modifier,
            imageVector = Icons.Default.ElectricBolt,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailCardPreview() {
    SehatyTheme {
        DetailNutrient(
            size = "0.3",
            nutrient = "Energy"
        )
    }
}