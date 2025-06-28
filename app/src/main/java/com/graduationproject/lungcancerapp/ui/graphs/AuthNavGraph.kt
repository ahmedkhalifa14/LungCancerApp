    package com.graduationproject.lungcancerapp.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

import com.graduationproject.lungcancerapp.ui.screens.auth.login.LoginScreen
import com.graduationproject.lungcancerapp.ui.screens.auth.register.RegisterScreen
import com.graduationproject.lungcancerapp.ui.screens.auth.register.UserFormScreen
import com.graduationproject.lungcancerapp.ui.screens.home.ScreenContent
import com.graduationproject.lungcancerapp.ui.screens.onboarding.OnBoardingScreen
import com.graduationproject.lungcancerapp.ui.screens.onboarding.OnBoardingScreenContent


    fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.SignUp.route
    ) {
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = AuthScreen.Forgot.route) {
            ScreenContent(name = AuthScreen.Forgot.route) {}
        }
        composable(route=AuthScreen.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(
            route = "USER_FORM/{userEmail}/{userPassword}",
            arguments = listOf(
                navArgument("userEmail") { type = NavType.StringType },
                navArgument("userPassword") { type = NavType.StringType }
            )
        ) {
            UserFormScreen(navController, it)
        }

    }
}

sealed class AuthScreen(val route: String) {
    data object OnBoarding : AuthScreen(route = "ON_BOARDING")
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
    data object Forgot : AuthScreen(route = "FORGOT")
    data object UserForm : AuthScreen(route = "USER_FORM")
}