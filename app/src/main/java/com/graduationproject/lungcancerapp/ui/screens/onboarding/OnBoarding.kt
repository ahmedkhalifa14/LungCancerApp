package com.graduationproject.lungcancerapp.ui.screens.onboarding

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.ui.graphs.AuthScreen
import com.graduationproject.lungcancerapp.ui.graphs.Graph
import com.graduationproject.lungcancerapp.ui.theme.AppMainColor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal
import com.graduationproject.lungcancerapp.ui.viewmodel.settings.SettingsViewModel

@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val isFirstTimeLaunch by viewModel.isFirstTimeLaunch.collectAsState(initial = null)
    val updatedLaunchState = rememberUpdatedState(newValue = isFirstTimeLaunch)
    val context = LocalContext.current
    LaunchedEffect(updatedLaunchState.value) {
        Log.d("isFirstTime", "Updated Value: ${updatedLaunchState.value}")
        if (updatedLaunchState.value == true) {
            navController.navigate(Graph.AUTHENTICATION) {
                popUpTo(AuthScreen.OnBoarding.route) {
                    inclusive = true
                }
            }
        }
    }

    OnBoardingScreenContent(
        onGetStartedClick = {
            viewModel.setFirstTimeLaunch(true)
        }
    )
}

@Composable
fun OnBoardingScreenContent(
    onGetStartedClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            painter = painterResource(id = R.drawable.lung_medical_icon),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.onboarding_title),
                fontSize = 28.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = Tajawal,

            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.onboarding_subtitle),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = FontWeight.Light,
                fontFamily = Tajawal
            )
            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppMainColor,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.get_started),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = Tajawal,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnBoardingScreenContent(onGetStartedClick = {})
}