package com.graduationproject.lungcancerapp.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.graduationproject.lungcancerapp.ui.graphs.Graph
import com.graduationproject.lungcancerapp.ui.theme.AppMainColor
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val listState = rememberLazyListState()

    // Track if the user is scrolling down
    val isScrollingDown = rememberScrollDirection(listState)

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                isVisible = !isScrollingDown // Hide Bottom Bar when scrolling down
            )
        }
    ) {
        HomeScreen(
            navigationController = navController,
            listState = listState,
            isScrollingDown = isScrollingDown
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController, isVisible: Boolean) {
    //val bottomBarBackgroundColor = getColor(AppColors.AppColorSet.AppScreenBackgroundColor)

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Settings,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any {
        it.route == currentDestination?.route || currentDestination?.route?.startsWith(
            Graph.Routes.PROFILE
        ) == true
    }

    // Use AnimatedVisibility to control bottom bar visibility
    AnimatedVisibility(
        visible = bottomBarDestination && isVisible,
        enter = slideInVertically(initialOffsetY = { it }), // Slide in from the bottom
        exit = slideOutVertically(targetOffsetY = { it })   // Slide out to the bottom
    ) {
        NavigationBar(
            modifier = Modifier.height(56.dp),
            containerColor = AppMainColor,
            tonalElevation = NavigationBarDefaults.Elevation,
            windowInsets = NavigationBarDefaults.windowInsets,
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = stringResource(screen.title), color = Color.White)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = Color.White
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun rememberScrollDirection(listState: LazyListState): Boolean {
    var lastScrollOffset by remember { mutableIntStateOf(0) }
    var lastIndex by remember { mutableIntStateOf(0) }

    return remember {
        derivedStateOf {
            val isScrollingDown = when {
                listState.firstVisibleItemIndex > lastIndex -> true
                listState.firstVisibleItemIndex < lastIndex -> false
                else -> listState.firstVisibleItemScrollOffset > lastScrollOffset
            }

            lastIndex = listState.firstVisibleItemIndex
            lastScrollOffset = listState.firstVisibleItemScrollOffset

            isScrollingDown
        }
    }.value
}