package com.graduationproject.lungcancerapp.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.graduationproject.lungcancerapp.ui.screens.home.MainScreen
import com.graduationproject.lungcancerapp.ui.screens.home.ScreenContent
import com.graduationproject.lungcancerapp.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME,
        startDestination = Graph.Routes.HOME
    ) {
        composable(route = Graph.Routes.HOME) {
            MainScreen(navController)
        }
        composable(route = Graph.Routes.SETTINGS) {
            ScreenContent(name = "SETTINGS") {
                navController.navigateUp()
            }
        }
        composable(route = Graph.Routes.PROFILE) {
            ProfileScreen(navController)
        }
        initialDiagnosisGraph(navController)
    }
}
