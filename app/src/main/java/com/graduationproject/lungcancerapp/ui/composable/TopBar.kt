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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.ui.theme.Tajawal

@Composable
fun TopBar() {
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .height(56.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
          //  painter = painterResource(id = R.drawable.left_arrow_icon),
            contentDescription = "left Arrow",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(32.dp)
                .graphicsLayer(
                    scaleX = if (isRtl) -1f else 1f,
                    scaleY = 1f
                )            ,
            tint = MaterialTheme.colorScheme.onBackground

        )
        Text(
            text = stringResource(R.string.find_doctors),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Tajawal,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
