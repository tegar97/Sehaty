package com.miftah.sehaty.ui.screens.navGraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object OnBoardingScreen : Route(route = "onBoardingScreen")

    data object AppStartNavigation : Route(route = "appStartNavigation")

    data object HomeScreen : Route(route = "homeScreen")

    data object HistoryScreen : Route(route = "historyScreen")

    data object ScanScreen : Route(route = "scanScreen")

    data object SettingScreen : Route(route = "settingScreen")

    data object GraphScreen : Route(route = "graphScreen")

    data object DetailScreen : Route(route = "detailScreen/{itemId}")

    data object MainNavigator : Route(route = "mainNavigator")
}