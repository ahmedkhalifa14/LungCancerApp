package com.graduationproject.lungcancerapp.common.utils

import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.data.model.Category
import com.graduationproject.lungcancerapp.data.model.Doctor
import com.graduationproject.lungcancerapp.data.model.Question

object Constants {
    fun getCategories(): List<Category>? {
        return listOf(
            Category(1, "Initial diagnosis", R.drawable.lung_medical_icon),
            Category(2, "Analysis AI", R.drawable.graph_analysis_analytics_icon),
            Category(3, "Rays", R.drawable.r_icon)
        )


    }

    fun getDoctorsList(): List<Doctor>? {
        return listOf(
            Doctor(
                id = 1,
                name = "Dr. Marcus Horizon",
                image = R.drawable.doctor_male_icon,
                specialty = "Cardiologist",
                distance = "800m away",
                rating = 4.7
            ),
            Doctor(
                id = 2,
                name = "Dr. Maria Silva",
                image = R.drawable.doctor_male_icon,
                specialty = "Pediatrician",
                distance = "1.2km away",
                rating = 4.9
            ),
            Doctor(
                id = 3,
                name = "Dr. Steve Carter",
                image = R.drawable.doctor_male_icon,
                specialty = "Orthopedist",
                distance = "500m away",
                rating = 4.5
            ),
            Doctor(
                id = 4,
                name = "Dr. Lucy Brown",
                image = R.drawable.doctor_male_icon,
                specialty = "Dermatologist",
                distance = "2.3km away",
                rating = 4.8
            )
        )
    }

    fun getQuestions(): List<Question>? {
        return listOf(
            Question(1, "Are the fingers yellow?"),
            Question(2, "Do you suffer from anxiety?"),
            Question(3, "Are you suffering from peer pressure?"),
            Question(4, "Do you have chronic diseases?"),
            Question(5, "Do you suffer from a chronic disease?"),
            Question(6, "Are you suffering from fatigue?"),
            Question(7, "Do you suffer from allergies?"),
            Question(8, "Do you suffer from wheezing?"),
            Question(9, "Are you consume alcohol?"),
            Question(10, "Do you suffer from conditions"),
            Question(11, "Do you have difficulty swallowing?"),
            Question(12, "Do your suffer from chest pain?"),
        )
    }
}