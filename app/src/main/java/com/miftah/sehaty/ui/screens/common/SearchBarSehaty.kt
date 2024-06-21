package com.miftah.sehaty.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.ui.theme.Grey30
import com.miftah.sehaty.ui.theme.Grey50
import com.miftah.sehaty.ui.theme.Grey70
import com.miftah.sehaty.ui.theme.SehatyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: ((String) -> Unit),
    onButtonPressed: () -> Unit
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(2f),
            shape = RoundedCornerShape(4.dp),
            placeholder = {
                Text(
                    text = "Search History",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Grey70
                    )
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onButtonPressed,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp)),
            shape = RectangleShape
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}

@Composable
fun Test(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: ((String) -> Unit)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(2f),
            shape = RoundedCornerShape(4.dp),
            placeholder = {
                Text(
                    text = "Search History",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Grey70
                    )
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { /* Handle search action */ },
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp)),
            shape = RectangleShape
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}


@Preview
@Composable
private fun MainSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SehatyTheme {
        MainSearchBar(
            query = "",
            onQueryChange = {

            }
        ){

        }
    }
}