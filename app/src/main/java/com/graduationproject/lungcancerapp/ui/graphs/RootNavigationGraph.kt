package com.graduationproject.lungcancerapp.ui.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.graduationproject.lungcancerapp.ui.screens.onboarding.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        composable(route = Graph.SPLASH) {
            SplashScreen(navController = navController)
        }
        homeNavGraph(navController = navController)
        initialDiagnosisGraph(navController = navController)

    }

}

object Graph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash"
    const val HOME = "home_graph"
    const val INITIAL_DIAGNOSIS = "InitialDiagnosis_graph"

    object Routes {
        const val HOME = "home"
        const val PROFILE = "profile"
        const val INITIAL_DIAGNOSIS = "InitialDiagnosis"
        const val SETTINGS = "settings"
    }
}