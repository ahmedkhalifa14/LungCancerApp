package com.graduationproject.lungcancerapp.data.repository

import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult


interface PredictionRepository {
    fun predict(inputFeatures: InputFeatures): PredictionResult
}