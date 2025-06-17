package com.graduationproject.lungcancerapp.ui.screens.InitialDiagnosis


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.graduationproject.lungcancerapp.ui.graphs.Graph
import com.graduationproject.lungcancerapp.ui.theme.Tajawal
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.ui.composable.CustomButton


@Composable
fun ResultScreen(
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {

    val probability = backStackEntry.arguments?.getFloat("probability")
    val hasLungCancer = backStackEntry.arguments?.getBoolean("hasLungCancer")
    ResultScreenContent(
        probability = probability, hasLungCancer = hasLungCancer,
        onBackToDiagnosisClick = {
            navController.navigateUp()
        },
        onBackToHome = {
            navController.navigate(Graph.HOME)
        }
    )
}

@SuppressLint("DefaultLocale")
@Composable
fun ResultScreenContent(
    probability: Float?,
    hasLungCancer: Boolean?,
    onBackToDiagnosisClick: () -> Unit,
    onBackToHome: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.done_icon),
                contentDescription = "Done",
                modifier = Modifier.size(128.dp)
            )


            Text(
                text = "Prediction Result",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Tajawal,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = if (hasLungCancer == true) {
                    "There is high probability of having the disease Please see a doctor (Probability: ${
                        String.format(
                            "%.2f",
                            probability
                        )
                    })"
                } else {
                    "Good You are not have the disease (Probability: ${
                        String.format(
                            "%.2f",
                            probability
                        )
                    })"
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Tajawal,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton("Retake") {
                    onBackToDiagnosisClick()
                }
               CustomButton("Home") {
                   onBackToHome()
               }

            }


        }
    }
}