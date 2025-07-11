package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.graduationproject.lungcancerapp.ui.theme.AppMainColor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = AppMainColor,
        fontWeight = FontWeight.Bold,
        fontFamily = Tajawal,
        fontSize = 14.sp,
        modifier = Modifier.clickable { onClick() }
    )

}