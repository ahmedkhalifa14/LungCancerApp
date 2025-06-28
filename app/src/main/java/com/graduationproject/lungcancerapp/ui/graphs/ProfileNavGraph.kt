package com.graduationproject.lungcancerapp.ui.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.graduationproject.lungcancerapp.ui.screens.profile.EditProfileScreen
import com.graduationproject.lungcancerapp.ui.screens.profile.ProfileScreen


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = Graph.Routes.PROFILE
    ) {
        composable(Graph.Routes.PROFILE) {
            ProfileScreen(navController)
        }
        composable(ProfileGraph.EditProfile.route) {
            EditProfileScreen()
        }
        composable(ProfileGraph.History.route) {
           // HistoryScreen(navController=navController)
        }
        composable(ProfileGraph.Notifications.route) {
           // NotificationSettingsScreen()
        }
        composable(ProfileGraph.SavedDoctors.route) {
           // SavedDoctorsScreen(navController)
        }

    }
}

sealed class ProfileGraph(val route: String) {
    data object EditProfile : ProfileGraph(route = "EDIT_PROFILE")
    data object History : ProfileGraph(route = "HISTORY")
    data object Notifications : ProfileGraph(route = "NOTIFICATIONS")
    data object SavedDoctors : ProfileGraph(route = "SAVED_DOCTORS")
}