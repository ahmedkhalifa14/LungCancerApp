package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graduationproject.lungcancerapp.ui.theme.AppMainColor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .height(56.dp)
            .clip(RoundedCornerShape(3.dp)),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = AppMainColor
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontFamily = Tajawal,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}