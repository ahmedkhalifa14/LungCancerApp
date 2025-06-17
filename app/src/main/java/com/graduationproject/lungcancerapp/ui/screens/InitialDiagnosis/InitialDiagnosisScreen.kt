package com.graduationproject.lungcancerapp.ui.screens.InitialDiagnosis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graduationproject.lungcancerapp.ui.composable.QuestionItem
import com.graduationproject.lungcancerapp.ui.theme.AppMainColor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal
import com.graduationproject.lungcancerapp.ui.viewmodel.LungCancerPredictionViewModel


@Composable
fun InitialDiagnosisScreen(
    navController: NavController,
    viewModel: LungCancerPredictionViewModel = hiltViewModel()
) {
    InitialDiagnosisScreenContent(viewModel, navController)
}


@Composable
fun InitialDiagnosisScreenContent(
    viewModel: LungCancerPredictionViewModel = hiltViewModel(),
    navController: NavController
) {
    val index = viewModel.currentQuestionIndex.value
    val question = viewModel.questions[index]

    val scrollState = rememberLazyListState()

    // Auto scroll to top on question change
    LaunchedEffect(index) {
        scrollState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())

            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(54.dp))
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Text(
                    text = "Question ${index + 1} of ${viewModel.questions.size}",
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                LinearProgressIndicator(
                    progress = viewModel.answeredCount / 12f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp), color = AppMainColor
                )
            }

            item {
                QuestionItem(
                    question = question,
                    onYesClick = { viewModel.onYesClick(index) },
                    onNoClick = { viewModel.onNoClick(index) }
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .height(56.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        onClick = { viewModel.goBack() },
                        enabled = index > 0
                    ) {
                        Text(
                            "Previous",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    if (index == viewModel.questions.lastIndex) {
                        Button(
                            shape = RoundedCornerShape(10),
                            modifier = Modifier
                                .height(56.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = AppMainColor
                            ),
                            onClick = {
                                viewModel.predict()?.let { result ->
                                    navController.navigate("RESULT/${result.probability}/${result.hasLungCancer}")
                                }
                            },
                            enabled = question.isSelectedYes.value || question.isSelectedNo.value
                        ) {
                            Text("Predict")
                        }
                    }
                }
            }

            item {
                if (viewModel.errorMessage.value.isNotEmpty()) {
                    Text(
                        text = viewModel.errorMessage.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

}
