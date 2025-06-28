package com.graduationproject.lungcancerapp.common.utils

import android.content.Context
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.data.model.Category
import com.graduationproject.lungcancerapp.data.model.Doctor
import com.graduationproject.lungcancerapp.data.model.Question

object Constants {
    fun getCategories(context: Context): List<Category>? {
        return listOf(
            Category(1, context.getString(R.string.initial_diagnosis), R.drawable.lung_medical_icon),
            Category(2,
                context.getString(R.string.analysis_ai), R.drawable.graph_analysis_analytics_icon),
            Category(3, context.getString(R.string.rays), R.drawable.r_icon),
        )
        
    }

    fun getDoctorsList(context: Context): List<Doctor>? {
        return listOf(
            Doctor(
                id = 1,
                name = "Dr. Marcus Horizon",
                image = R.drawable.doctor_male_icon,
                specialty = context.getString(R.string.cardiologist),
                distance = context.getString(R.string._800m_away),
                rating = 4.7
            ),
            Doctor(
                
                id = 2,
                name = "Dr. Maria Silva",
                image = R.drawable.doctor_male_icon,
                specialty = context.getString(R.string.pediatrician),
                distance = context.getString(R.string._1_2km_away),
                rating = 4.9
                
            ),
            Doctor(
                id = 3,
                name = "Dr. Steve Carter",
                image = R.drawable.doctor_male_icon,
                specialty = context.getString(R.string.orthopedist),
                distance = context.getString(R.string._500m_away),
                rating = 4.5
                
            ),
            Doctor(
                id = 4,
                name = "Dr. Lucy Brown",
                image = R.drawable.doctor_male_icon,
                specialty = context.getString(R.string.dermatologist),
                distance = context.getString(R.string._2_3km_away),
                rating = 4.8
            )
        )
    }

    fun getQuestions(context: Context): List<Question>? {
        return listOf(
            Question(1, context.getString(R.string.are_the_fingers_yellow)),
            Question(2, context.getString(R.string.do_you_suffer_from_anxiety)),
            Question(3, context.getString(R.string.are_you_suffering_from_peer_pressure)),
            Question(4, context.getString(R.string.do_you_have_chronic_diseases)),
            Question(5, context.getString(R.string.are_you_suffering_from_fatigue)),
            Question(6, context.getString(R.string.do_you_suffer_from_allergies)),
            Question(7, context.getString(R.string.do_you_suffer_from_wheezing)),
            Question(8, context.getString(R.string.are_you_consume_alcohol)),
            Question(9, context.getString(R.string.do_you_suffer_from_conditions)),
            Question(10, context.getString(R.string.do_you_have_difficulty_swallowing)),
            Question(11, context.getString(R.string.do_your_suffer_from_chest_pain)),
            Question(12,
                context.getString(R.string.are_you_worried_about_physical_changes_such_as_yellowing_of_your_fingers)),

        )
    }
}