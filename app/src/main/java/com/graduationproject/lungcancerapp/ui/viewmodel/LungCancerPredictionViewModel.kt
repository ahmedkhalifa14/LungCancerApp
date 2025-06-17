package com.graduationproject.lungcancerapp.ui.viewmodel


//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import com.graduationproject.lungcancerapp.data.model.InputFeatures
//import com.graduationproject.lungcancerapp.domin.PredictLungCancerUseCase
//import javax.inject.Inject
//
//class LungCancerPredictionViewModel @Inject constructor(
//    private val predictUseCase: PredictLungCancerUseCase
//) : ViewModel() {
//
//    val inputFields = mutableStateListOf<String>().apply {
//        addAll(List(12) { "" })
//    }
//    val predictionResult = mutableStateOf("")
//    val errorMessage = mutableStateOf("")
//
//    fun onInputChanged(index: Int, value: String) {
//        inputFields[index] = value
//    }
//
//    fun predict() {
//        try {
//            val inputs = inputFields.mapIndexed { index, value ->
//                value.toFloatOrNull() ?: run {
//                    errorMessage.value = "Invalid input in field ${index + 1}"
//                    return
//                }
//            }
//            if (inputs.size != 12) {
//                errorMessage.value = "Please fill all fields"
//                return
//            }
//
//            val inputFeatures = InputFeatures(
//                yellowFingers = inputs[0],
//                anxiety = inputs[1],
//                peerPressure = inputs[2],
//                chronicDisease = inputs[3],
//                fatigue = inputs[4],
//                allergy = inputs[5],
//                wheezing = inputs[6],
//                alcoholConsuming = inputs[7],
//                coughing = inputs[8],
//                shortnessOfBreath = inputs[9],
//                swallowingDifficulty = inputs[10],
//                chestPain = inputs[11]
//            )
//
//            val result = predictUseCase(inputFeatures)
//            predictionResult.value = if (result.hasLungCancer) {
//                "Lung Cancer: Yes (Probability: ${String.format("%.2f", result.probability)})"
//            } else {
//                "Lung Cancer: No (Probability: ${String.format("%.2f", result.probability)})"
//            }
//            errorMessage.value = ""
//        } catch (e: Exception) {
//            errorMessage.value = "Prediction error: ${e.message}"
//        }
//    }
//}





import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.graduationproject.lungcancerapp.common.utils.Constants.getQuestions
import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult
import com.graduationproject.lungcancerapp.data.model.Question
import com.graduationproject.lungcancerapp.domin.PredictLungCancerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LungCancerPredictionViewModel @Inject constructor(
    private val predictUseCase: PredictLungCancerUseCase
) : ViewModel() {

    private val _questions = mutableStateListOf<Question>().apply {
        addAll(getQuestions()!!)
    }
    val questions: List<Question> get() = _questions

    val currentQuestionIndex = mutableStateOf(0)
    val errorMessage = mutableStateOf("")
    val allQuestionsAnswered = mutableStateOf(false)

    val answeredCount: Int
        get() = _questions.count { it.isSelectedYes.value || it.isSelectedNo.value }

    init {
        updateAllQuestionsAnswered()
    }

    fun onYesClick(index: Int) {
        _questions[index].isSelectedYes.value = true
        _questions[index].isSelectedNo.value = false
        updateAllQuestionsAnswered()
        if (index < _questions.lastIndex) {
            currentQuestionIndex.value++
        }
    }

    fun onNoClick(index: Int) {
        _questions[index].isSelectedYes.value = false
        _questions[index].isSelectedNo.value = true
        updateAllQuestionsAnswered()
        if (index < _questions.lastIndex) {
            currentQuestionIndex.value++
        }
    }

    fun updateAllQuestionsAnswered() {
        allQuestionsAnswered.value = _questions.all { it.isSelectedYes.value || it.isSelectedNo.value }
    }

    fun goNext() {
        if (currentQuestionIndex.value < _questions.lastIndex) {
            currentQuestionIndex.value++
        }
    }

    fun goBack() {
        if (currentQuestionIndex.value > 0) {
            currentQuestionIndex.value--
        }
    }

    fun resetQuestions() {
        _questions.forEach {
            it.isSelectedYes.value = false
            it.isSelectedNo.value = false
        }
        currentQuestionIndex.value = 0
        updateAllQuestionsAnswered()
        errorMessage.value = ""
    }

    fun predict(): PredictionResult? {
        if (!allQuestionsAnswered.value) {
            errorMessage.value = "Please answer all questions"
            return null
        }

        return try {
            val inputs = _questions.map { question ->
                if (question.isSelectedYes.value) 1f else 0f
            }

            if (inputs.size != 12) {
                errorMessage.value = "Invalid number of inputs"
                return null
            }

            val inputFeatures = InputFeatures(
                yellowFingers = inputs[0],
                anxiety = inputs[1],
                peerPressure = inputs[2],
                chronicDisease = inputs[3],
                fatigue = inputs[4],
                allergy = inputs[5],
                wheezing = inputs[6],
                alcoholConsuming = inputs[7],
                coughing = inputs[8],
                shortnessOfBreath = inputs[9],
                swallowingDifficulty = inputs[10],
                chestPain = inputs[11]
            )

            errorMessage.value = ""
            predictUseCase(inputFeatures)
        } catch (e: Exception) {
            errorMessage.value = "Prediction error: ${e.message}"
            null
        }
    }
}
