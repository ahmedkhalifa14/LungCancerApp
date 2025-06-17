package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.graduationproject.lungcancerapp.data.model.Doctor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal

@Composable
fun DoctorCard(
    doctor: Doctor,
    onClickDoctorCard: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val cardWidth = screenWidthDp - 32.dp

    Card(
        modifier = Modifier
            .width(cardWidth)
            .background(MaterialTheme.colorScheme.surface)
            .padding(2.dp)
            .clickable(
                onClick = onClickDoctorCard
            )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Load and display the doctor's image
            val painter = rememberAsyncImagePainter(
                model = doctor.image,
                placeholder = painterResource(id = android.R.drawable.ic_dialog_info),
                error = painterResource(id = android.R.drawable.ic_dialog_info)
            )
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurface)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Doctor Image",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = doctor.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Tajawal,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = doctor.specialty,
                    fontSize = 16.sp,
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.star_on),
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF2196F3)
                    )
                    Text(
                        text = "${doctor.rating}",
                        fontSize = 14.sp,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.padding(start = 4.dp),
                        fontWeight = FontWeight.Normal,
                        fontFamily = Tajawal,
                    )
                    Text(
                        text = doctor.distance,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Tajawal,
                    )
                }
            }
        }
    }
}