package com.graduationproject.lungcancerapp.data.model


data class PredictionResult(
    val probability: Float,
    val hasLungCancer: Boolean
)