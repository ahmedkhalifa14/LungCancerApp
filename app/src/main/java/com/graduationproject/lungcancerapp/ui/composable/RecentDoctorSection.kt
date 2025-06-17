package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.graduationproject.lungcancerapp.R.string
import com.graduationproject.lungcancerapp.data.model.Category
import com.graduationproject.lungcancerapp.data.model.Doctor
import kotlin.collections.forEach


@Composable
fun RecentDoctorSection(
    doctors: List<Doctor>?,
    isLoading: Boolean,
    errorMessage: String?,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        if (isLoading) {
            repeat(5) { ShimmerCircularCategoryCard() }
        } else if (errorMessage != null) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(string.error_loading_categories, errorMessage))
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else if (doctors != null) {

            doctors.forEach { doctor ->
                RecentDoctorCard(doctor = doctor)
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else {

            Text(stringResource(string.no_categories_available))
        }
    }
}