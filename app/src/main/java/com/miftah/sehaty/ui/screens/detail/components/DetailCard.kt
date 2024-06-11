package com.miftah.sehaty.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun DetailCard(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
    IconButton(
        modifier = modifier.size(50.dp),
        onClick = {

        },
        colors = IconButtonDefaults.iconButtonColors().copy(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
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
        DetailCard()
    }
}