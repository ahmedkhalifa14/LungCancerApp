package com.graduationproject.lungcancerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.graduationproject.lungcancerapp.ui.graphs.RootNavigationGraph
import com.graduationproject.lungcancerapp.ui.theme.LungCancerAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LungCancerAppTheme {
                RootNavigationGraph(navController = navController)
            }
        }
    }
}

