package com.miftah.sehaty.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun DetailCard(modifier: Modifier = Modifier) {
    ElevatedCard {
        Row {
            IconDetailCard()
            Spacer(modifier = modifier.width(16.dp))
            Column {
                Text("1000")
                Spacer(modifier = modifier.width(8.dp))
                Text(text = "Energy")
            }
        }
    }
}

@Composable
fun IconDetailCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Blue),
    ) {
        Icon(imageVector = Icons.Default.ElectricBolt, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailCardPreview() {
    SehatyTheme {
        DetailCard()
    }
}