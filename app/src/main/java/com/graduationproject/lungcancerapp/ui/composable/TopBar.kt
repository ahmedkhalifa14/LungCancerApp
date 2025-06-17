package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.ui.theme.Tajawal

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .height(56.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.left_arrow_icon),
            contentDescription = "left Arrow",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(32.dp),
            tint = MaterialTheme.colorScheme.onBackground

        )
        Text(
            text = "Find Doctors",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Tajawal,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
