package com.graduationproject.lungcancerapp.data.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Question(
    val id: Int,
    val question: String,
    val isSelectedYes: MutableState<Boolean> = mutableStateOf(false),
    val isSelectedNo: MutableState<Boolean> = mutableStateOf(false)
)
