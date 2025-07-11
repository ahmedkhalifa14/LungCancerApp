package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.data.model.Category
import com.graduationproject.lungcancerapp.data.model.Doctor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal

@Composable
fun RecentDoctorCard(doctor: Doctor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        val defaultImage = painterResource(R.drawable.ic_launcher_background)
        val painter = if (doctor.image != 0) {
            rememberAsyncImagePainter(
                model = doctor.image,
                placeholder = defaultImage,
                error = defaultImage,
                fallback = defaultImage
            )
        } else {
            defaultImage
        }


        Box(
            modifier = Modifier
                .size(78.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                modifier = Modifier.fillMaxSize(),
                contentDescription = "categoryImage",
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = doctor.name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = Tajawal,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.width(80.dp)
        )
    }
}