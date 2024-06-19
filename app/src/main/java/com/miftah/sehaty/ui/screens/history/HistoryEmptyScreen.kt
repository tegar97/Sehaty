package com.miftah.sehaty.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun HistoryEmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Entries"
        )
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    SehatyTheme {
        Surface(
            modifier = Modifier
        ) {
            HistoryEmptyScreen(
                modifier = Modifier
            )
        }
    }
}