package com.graduationproject.lungcancerapp.ui.graphs

import androidx.compose.ui.graphics.GraphicsContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.graduationproject.lungcancerapp.ui.screens.InitialDiagnosis.InitialDiagnosisScreen
import com.graduationproject.lungcancerapp.ui.screens.InitialDiagnosis.ResultScreen


fun NavGraphBuilder.initialDiagnosisGraph(navController: NavHostController) {
    navigation(
        route = Graph.INITIAL_DIAGNOSIS,
        startDestination = InitialDiagnosisGraph.InitialDiagnosis.route
    ) {
        composable(InitialDiagnosisGraph.InitialDiagnosis.route) {
            InitialDiagnosisScreen(navController)
        }
        composable(
            InitialDiagnosisGraph.ResultScreen.route,
            arguments = listOf(
                navArgument("probability") { type = NavType.FloatType },
                navArgument("hasLungCancer") { type = NavType.BoolType }
            )
        ) {
            ResultScreen(navController,it)
        }
    }
}

sealed class InitialDiagnosisGraph(val route: String) {
    data object InitialDiagnosis : InitialDiagnosisGraph(route = "INITIAL_DIAGNOSIS")
    data object ResultScreen : InitialDiagnosisGraph(route = "RESULT/{probability}/{hasLungCancer}")

}
