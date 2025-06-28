package com.graduationproject.lungcancerapp.ui.screens.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.ui.graphs.AuthScreen
import com.graduationproject.lungcancerapp.ui.graphs.Graph
import com.graduationproject.lungcancerapp.ui.theme.AppMainColor
import com.graduationproject.lungcancerapp.ui.viewmodel.settings.SettingsViewModel


@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    val isFirstTimeLaunch by viewModel.isFirstTimeLaunch.collectAsState(initial = null)
    val isUserLoggedIn by viewModel.isUserLogin.collectAsState(initial = null)
    viewModel.checkFirstTimeLaunch()
    viewModel.checkUserLogin()
    LaunchedEffect(isFirstTimeLaunch, isUserLoggedIn) {
        if (isFirstTimeLaunch != null && isUserLoggedIn != null) {
            scale.animateTo(
                targetValue = 0.9f, animationSpec = tween(
                    durationMillis = 800, easing = overshootEasing(
                        8f
                    )
                )
            )
            alpha.animateTo(
                targetValue = 1f, animationSpec = tween(
                    durationMillis = 800
                )
            )
            kotlinx.coroutines.delay(1000)
            when {
                !isFirstTimeLaunch!! -> {
                    navController.navigate(AuthScreen.OnBoarding.route) {
                        popUpTo(Graph.SPLASH) { inclusive = true }
                    }
                }

                !isUserLoggedIn!! -> {

                    navController.navigate(Graph.AUTHENTICATION) {
                        popUpTo(Graph.SPLASH) { inclusive = true }
                    }
                }

                else -> {
                    navController.navigate(Graph.HOME) {
                        popUpTo(Graph.SPLASH) { inclusive = true }
                    }
                }
            }
        }

    }
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.lung_medical_icon),
            contentDescription = "Splash Logo",
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
                .size(250.dp)
        )
    }
}


private class OvershootInterpolator(private val tension: Float) : Easing {
    override fun transform(fraction: Float): Float {
        val t = fraction * 2f - 1f
        return t * t * ((tension + 1) * t + tension) + 1f
    }
}

fun overshootEasing(tension: Float): Easing {
    return OvershootInterpolator(tension)
}


fun navigate(navController: NavHostController, graph: String) {
    navController.navigate(graph) {
        popUpTo(Graph.SPLASH) { inclusive = true }
    }
}