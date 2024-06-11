package com.miftah.sehaty.ui.screens.navigator

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miftah.sehaty.ui.screens.history.HistoryScreen
import com.miftah.sehaty.ui.screens.mainActivity.MainActivity
import com.miftah.sehaty.ui.screens.mainActivity.MainViewModel
import com.miftah.sehaty.ui.screens.mainActivity.openAppSettings
import com.miftah.sehaty.ui.screens.navGraph.Route
import com.miftah.sehaty.ui.screens.navigator.navigators.BottomNavigationItem
import com.miftah.sehaty.ui.screens.navigator.navigators.MainBottomBar
import com.miftah.sehaty.ui.screens.scan.ScanScreen
import com.miftah.sehaty.ui.screens.scan.ScanViewModel
import com.miftah.sehaty.utils.CameraPermissionTextProvider
import com.miftah.sehaty.utils.PermissionDialog

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

                    }
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