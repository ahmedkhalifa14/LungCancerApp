package com.graduationproject.lungcancerapp.data.datasource

import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult

interface ModelDataSource {
    fun predict(inputFeatures: InputFeatures): PredictionResult
    fun close()
}



/*

package com.example.lungcancerprediction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.lungcancerprediction.data.datasource.TFLiteModelDataSource
import com.example.lungcancerprediction.data.repository.PredictionRepositoryImpl
import com.example.lungcancerprediction.domain.usecase.PredictLungCancerUseCase
import com.example.lungcancerprediction.presentation.LungCancerPredictionScreen
import com.example.lungcancerprediction.presentation.LungCancerPredictionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize dependencies
        val modelDataSource = TFLiteModelDataSource(this, "lung_cancer_model.tflite")
        val repository = PredictionRepositoryImpl(modelDataSource)
        val useCase = PredictLungCancerUseCase(repository)
        val viewModel = LungCancerPredictionViewModel(useCase)

        setContent {
            LungCancerPredictionScreen(viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // Preview placeholder (no dependencies for preview)
    LungCancerPredictionScreen(null)
}

 */


/*

package com.example.lungcancerprediction.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LungCancerPredictionScreen(viewModel: LungCancerPredictionViewModel?) {
    val featureNames = listOf(
        "Gender (0 or 1)", "Age", "Smoking (0 or 1)", "Yellow Fingers (0 or 1)",
        "Anxiety (0 or 1)", "Peer Pressure (0 or 1)", "Chronic Disease (0 or 1)",
        "Fatigue (0 or 1)", "Allergy (0 or 1)", "Wheezing (0 or 1)",
        "Alcohol Consuming (0 or 1)", "Coughing (0 or 1)",
        "Shortness of Breath (0 or 1)", "Swallowing Difficulty (0 or 1)",
        "Chest Pain (0 or 1)"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Input fields
            items(featureNames.size) { index ->
                TextField(
                    value = viewModel?.inputFields?.get(index) ?: "",
                    onValueChange = { viewModel?.onInputChanged(index, it) },
                    label = { Text(featureNames[index]) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel != null
                )
            }

            // Predict button
            item {
                Button(
                    onClick = { viewModel?.predict() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    enabled = viewModel != null
                ) {
                    Text("Predict")
                }
            }

            // Result text
            item {
                Text(
                    text = viewModel?.predictionResult?.value ?: "Prediction: ",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            // Error message
            item {
                Text(
                    text = viewModel?.errorMessage?.value ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
 */


/*
package com.example.lungcancerprediction.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lungcancerprediction.domain.model.Question

@Composable
fun LungCancerPredictionScreen(
    viewModel: LungCancerPredictionViewModel?,
    navController: NavController?
) {
    LungCancerPredictionScreenContent(viewModel, navController)
}

@Composable
fun LungCancerPredictionScreenContent(
    viewModel: LungCancerPredictionViewModel?,
    navController: NavController?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Initial Diagnosis",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Default, // Replace with Tajawal
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        item {
            Text(
                text = "⚠ You must answer all questions.",
                fontSize = 20.sp,
                color = Color.Red,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Default, // Replace with Tajawal
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(viewModel?.questions?.size ?: 0) { index ->
            val question = viewModel?.questions?.get(index)
            if (question?.isNumerical == true) {
                TextField(
                    value = if (question.isSelectedYes.value) question.text else "",
                    onValueChange = { viewModel?.onAgeInputChanged(it) },
                    label = { Text(question.text) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel != null
                )
            } else {
                QuestionItem(
                    question = question,
                    onYesClick = { viewModel?.onYesClick(index) },
                    onNoClick = { viewModel?.onNoClick(index) },
                    enabled = viewModel != null
                )
            }
        }
        item {
            Button(
                onClick = {
                    viewModel?.predict()?.let { result ->
                        navController?.navigate(
                            "result/${result.probability}/${result.hasLungCancer}"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                enabled = viewModel?.allQuestionsAnswered?.value == true
            ) {
                Text("Predict")
            }
        }
        item {
            Text(
                text = viewModel?.errorMessage?.value ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun QuestionItem(
    question: Question?,
    onYesClick: () -> Unit,
    onNoClick: () -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = question?.text ?: "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Row {
            Button(
                onClick = onYesClick,
                enabled = enabled && !(question?.isSelectedYes?.value ?: false)
            ) {
                Text("Yes")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onNo NoClick,
                enabled = enabled && !(question?.isSelectedNo?.value ?: false)
            ) {
                Text("No")
            }
        }
    }
}
 */