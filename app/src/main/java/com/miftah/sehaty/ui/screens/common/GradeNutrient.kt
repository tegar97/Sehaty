package com.miftah.sehaty.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun GradeNutrient(
    modifier: Modifier = Modifier,
    fontSize: Int,
    percentage: Float,
    indicatorSize: Int,
    strokeWidth: Int,
    indicatorColor: Color
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "A",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = fontSize.sp,
                color = indicatorColor
            )
        )
        CircularProgressIndicator(
            strokeWidth = strokeWidth.dp,
            strokeCap = StrokeCap.Round,
            progress = { percentage },
            color = indicatorColor,
            modifier = Modifier.size(indicatorSize.dp),
            trackColor = Color.Gray
        )
    }
}

@Preview
@Composable
private fun GradeNutrientPreview() {
    SehatyTheme {
        GradeNutrient(
            fontSize = 25,
            indicatorSize = 50,
            percentage = 0.6f,
            strokeWidth = 5,
            indicatorColor = Color.Red
        )
    }
}