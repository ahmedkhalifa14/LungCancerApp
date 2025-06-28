package com.graduationproject.lungcancerapp.domin

import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult
import com.graduationproject.lungcancerapp.data.repository.prediction.PredictionRepository


class PredictLungCancerUseCase(
    private val repository: PredictionRepository
) {
    operator fun invoke(inputFeatures: InputFeatures): PredictionResult {
        return repository.predict(inputFeatures)
    }
}