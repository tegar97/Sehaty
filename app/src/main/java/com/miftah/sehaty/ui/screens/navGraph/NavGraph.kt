package com.miftah.sehaty.ui.screens.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.miftah.sehaty.ui.screens.navigator.MainNavigator
import com.miftah.sehaty.ui.screens.onboarding.OnBoardingScreen
import com.miftah.sehaty.ui.screens.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.OnBoardingApp.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel : OnBoardingViewModel =  hiltViewModel()
                OnBoardingScreen(
                    modifier = Modifier,
                    state = viewModel.onBoardingState.value,
                    onEvent = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                MainNavigator()
            }
        }
    }
}