package com.miftah.sehaty.ui.screens.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.ui.screens.navGraph.Route
import com.miftah.sehaty.ui.theme.SehatyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBar(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    query: String,
    onSearch: ((String) -> Unit),
    onQueryChange: ((String) -> Unit),
) {
    SearchBar(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp)),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isActive,
        onActiveChange = {

        },
        leadingIcon = {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        },
    ) {

    }
}

@Preview
@Composable
private fun MainSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SehatyTheme {
        MainSearchBar(isActive = false, query = "", onSearch = {}) {

        }
    }
}