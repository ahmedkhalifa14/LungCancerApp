package com.graduationproject.lungcancerapp.ui.screens.home

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.ui.graphs.Graph

sealed class BottomBarScreen (
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
){
    data object Home: BottomBarScreen(
        route = Graph.Routes.HOME,
        title = R.string.home,
        icon = Icons.Default.Home
    )
    data object Settings: BottomBarScreen(
        route = Graph.Routes.SETTINGS,
        title = R.string.settings,
        icon = Icons.Default.Settings
    )
    data object Profile: BottomBarScreen(
        route = Graph.Routes.PROFILE,
        title = R.string.me,
        icon = Icons.Default.Person
    )

}