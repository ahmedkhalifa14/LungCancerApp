package com.graduationproject.lungcancerapp.data.repository.prediction

import com.graduationproject.lungcancerapp.data.local.tflite.ModelDataSource
import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult


class PredictionRepositoryImpl(
    private val modelDataSource: ModelDataSource
) : PredictionRepository {
    override fun predict(inputFeatures: InputFeatures): PredictionResult {
        return modelDataSource.predict(inputFeatures)
    }
}