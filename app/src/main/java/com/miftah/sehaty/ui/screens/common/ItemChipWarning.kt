package com.miftah.sehaty.ui.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.ui.screens.history.components.ChipAndWarning
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun ItemChipWarning(
    modifier: Modifier = Modifier,
    itemChip: ChipAndWarning
) {
    AssistChip(
        modifier = modifier,
        onClick = {

        },
        label = {
            Text(
                text = itemChip.title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 8.sp
                )
            )
        },
        colors = SuggestionChipDefaults.suggestionChipColors().copy(
            labelColor = itemChip.color,
            containerColor = itemChip.color
        )
    )
}

data class ChipAndWarning(
    val title: String,
    val color: Color
)

@Preview(showBackground = true)
@Composable
private fun ItemWarningChipPreview() {
    SehatyTheme {
        ItemChipWarning(
            itemChip = ChipAndWarning(
                title = "Gula",
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        )
    }
}