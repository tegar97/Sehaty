package com.miftah.sehaty.ui.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.ui.theme.Grey30
import com.miftah.sehaty.ui.theme.SehatyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSehatySecondary(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange : (String) -> Unit
) {
    SearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {

        },
        active = false,
        onActiveChange = {

        },
        shape = RoundedCornerShape(15.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow.copy(
                alpha = 0.9f
            )
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {

        }
    ) {

    }
}

@Preview
@Composable
private fun SearchBarSehatySecondaryPreview() {
    SehatyTheme {
        val query by remember { mutableStateOf("") }
        SearchBarSehatySecondary(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = {

            }
        )
    }
}
