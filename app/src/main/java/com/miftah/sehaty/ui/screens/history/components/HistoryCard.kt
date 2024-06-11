package com.miftah.sehaty.ui.screens.history.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun HistoryCard(
    modifier: Modifier = Modifier,
    urlImage: String,
    itemName: String,
    itemsChip: List<ChipAndWarning>
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row {
            AsyncImage(
                model = urlImage,
                contentDescription = null,
                modifier = modifier.size(80.dp)
            )
            Column {
                Text(text = itemName, style = MaterialTheme.typography.titleMedium)
                Row {
                    itemsChip.forEach {
                        SuggestionChip(
                            modifier = modifier,
                            onClick = {

                            },
                            label = {
                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 8.sp
                                    )
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors().copy(
                                labelColor = it.color,
                            ),
                        )
                        Spacer(modifier = modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

data class ChipAndWarning(
    val title: String,
    val color: Color
)


@Preview
@Composable
private fun HistoryCardPrev() {
    SehatyTheme {
        HistoryCard(
            urlImage = "https://tabris.com/wp-content/uploads/2021/06/jetpack-compose-icon_RGB.png",
            itemName = "Biscuit",
            itemsChip = listOf(ChipAndWarning(title = "Gula", color = MaterialTheme.colorScheme.onErrorContainer), ChipAndWarning(title = "Gula", color = MaterialTheme.colorScheme.onErrorContainer))
        )
    }
}