package com.graduationproject.lungcancerapp.data.local.tflite

import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult

interface ModelDataSource {
    fun predict(inputFeatures: InputFeatures): PredictionResult
    fun close()
}