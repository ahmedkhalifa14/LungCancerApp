package com.graduationproject.lungcancerapp.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmedkhalifa.careerlinkapp.composable.SpacerHorizontal16
import com.graduationproject.lungcancerapp.ui.theme.Tajawal


@Composable
fun ProfileCard(
    painter: Painter,
    cardTitle: String,
    onClick: () -> Unit
) {
        val backgroundColor = MaterialTheme.colorScheme.background
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    )
    {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileCardCircularImage(painter)
            SpacerHorizontal16()
            Text(
                text = cardTitle,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                fontFamily = Tajawal
            )
        }
    }

}