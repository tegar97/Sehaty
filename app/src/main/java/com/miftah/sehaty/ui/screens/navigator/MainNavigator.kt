package com.miftah.sehaty.ui.screens.navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miftah.sehaty.ui.screens.history.HistoryScreen
import com.miftah.sehaty.ui.screens.navGraph.Route
import com.miftah.sehaty.ui.screens.navigator.navigators.BottomNavigationItem
import com.miftah.sehaty.ui.screens.navigator.navigators.MainBottomBar
import com.miftah.sehaty.ui.screens.scan.ScanScreen
import com.miftah.sehaty.ui.screens.scan.ScanViewModel

@Composable
fun MainNavigator(
    startDestination: Route = Route.HistoryScreen,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavigationItem(Icons.Default.History, "Scan History"),
        BottomNavigationItem(Icons.Default.DocumentScanner, "Scan"),
        BottomNavigationItem(Icons.Default.Favorite, "Favorite"),
    )
    var selectedItem by remember { mutableIntStateOf(0) }

    val backStackState = navController.currentBackStackEntryAsState().value

    val isBottomBarVisible by remember(backStackState) {
        derivedStateOf {
            backStackState?.destination?.route == Route.HomeScreen.route ||
                    backStackState?.destination?.route == Route.HistoryScreen.route ||
                    backStackState?.destination?.route == Route.SettingScreen.route
        }
    }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                MainBottomBar(
                    items = items,
                    onSelectedChange = {
                        selectedItem = it
                        when (it) {
                            0 -> navController.navigate(Route.HistoryScreen.route)
                            1 -> navController.navigate(Route.ScanScreen.route)
                            2 -> navController.navigate(Route.SettingScreen.route)
                        }
                    },
                    selectedItem = selectedItem
                )
            }

        }
    ) { innerPadding ->

        val bottomPadding = innerPadding.calculateBottomPadding()
        val topPadding = innerPadding.calculateTopPadding()

        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Route.HistoryScreen.route) {
                var query by remember { mutableStateOf("") }
                HistoryScreen(
                    titleScreen = "Scan History",
                    query = query,
                    onQueryChange = {
                        query = it
                    },
                    onSearch = {

                    },
                    historyScanned = listOf()
                )
            }

            composable(route = Route.ScanScreen.route) {
                val viewModel: ScanViewModel = hiltViewModel()

                ScanScreen()

            }
            composable(route = Route.SettingScreen.route) {

            }
            composable(route = Route.DetailScreen.route) {

            }
        }

    }
}