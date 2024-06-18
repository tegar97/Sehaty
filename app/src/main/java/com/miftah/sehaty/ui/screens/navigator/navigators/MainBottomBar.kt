package com.miftah.sehaty.ui.screens.navigator.navigators

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    onSelectedChange: ((Int) -> Unit),
    selectedItem: Int,
) {
    BottomAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(30)),
        containerColor = Color.Red
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (index == 1) {
                        Box(
                            modifier
                                .size(MaterialTheme.dimens.large)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = item.icon, contentDescription = null)
                        }
                    } else {
                        Icon(imageVector = item.icon, contentDescription = null)
                    }

                },
                label = {
                    if (index != 1) Text(item.label)
                },
                selected = selectedItem == index,
                onClick = {
                    if (index != 1) onSelectedChange(index)
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String
)

@Preview()
@Composable
private fun MainBottomBarPrev() {
    val items = listOf(
        BottomNavigationItem(Icons.Default.Home, "Home"),
        BottomNavigationItem(Icons.Default.DocumentScanner, "Scan"),
        BottomNavigationItem(Icons.Default.Favorite, "Favorite"),
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    SehatyTheme {
        Scaffold(
            modifier = Modifier.background(Color.Gray),
            bottomBar = {
                MainBottomBar(
                    modifier = Modifier.padding(8.dp),
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