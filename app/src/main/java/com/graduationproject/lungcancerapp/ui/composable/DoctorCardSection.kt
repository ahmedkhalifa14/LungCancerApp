package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.graduationproject.lungcancerapp.data.model.Doctor
import com.graduationproject.lungcancerapp.R


@Composable
fun DoctorCardSection(
    doctors: List<Doctor>?,
    isLoading: Boolean,
    errorMessage: String?,
    onDoctorCardClick: (Doctor) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        when {
            isLoading -> {
                HorizontalPager(
                    count = 5,
                    state = rememberPagerState(),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    itemSpacing = 16.dp
                ) { page ->
                    ShimmerDoctorCard()
                }
            }
            errorMessage != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.error_loading_Doctors, errorMessage))
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            doctors != null -> {
                HorizontalPager(
                    count = doctors.size,
                    state = rememberPagerState(),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 2.dp),
                    itemSpacing = 16.dp
                ) { page ->
                    DoctorCard(
                        doctor = doctors[page],
                        onClickDoctorCard = { onDoctorCardClick(doctors[page]) }
                    )
                }
            }
            else -> {
                Text(stringResource(R.string.no_doctors_available))
            }
        }
    }
}