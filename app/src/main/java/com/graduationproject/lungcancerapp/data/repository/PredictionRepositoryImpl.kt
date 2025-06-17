package com.graduationproject.lungcancerapp.data.repository

import com.graduationproject.lungcancerapp.data.datasource.ModelDataSource
import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult


class PredictionRepositoryImpl(
    private val modelDataSource: ModelDataSource
) : PredictionRepository {
    override fun predict(inputFeatures: InputFeatures): PredictionResult {
        return modelDataSource.predict(inputFeatures)
    }
}