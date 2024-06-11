package com.miftah.sehaty.ui.screens.navigator.navigators

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miftah.sehaty.ui.theme.SehatyTheme

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    onSelectedChange: ((Int) -> Unit),
    selectedItem: Int,
) {
    BottomAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    onSelectedChange(index)
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String
)

@Preview
@Composable
private fun MainBottomBarPrev() {
    val items = listOf(
        BottomNavigationItem(Icons.Default.History, "Scan History"),
        BottomNavigationItem(Icons.Default.DocumentScanner, "Scan"),
        BottomNavigationItem(Icons.Default.Favorite, "Favorite"),
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    SehatyTheme {
        Scaffold(
            bottomBar = {
                MainBottomBar(
                    items = items,
                    onSelectedChange = {
                        selectedItem = it
                    },
                    selectedItem = selectedItem
                )
            }
        ) {
            Surface(
                modifier = Modifier.padding(it)
            ) {

            }
        }
    }
}