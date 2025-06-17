package com.graduationproject.lungcancerapp.ui.composable

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.data.model.Question

@Composable
fun QuestionItem(
    question: Question,
    onYesClick: () -> Unit,
    onNoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = question.question,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onYesClick,
                    enabled = !question.isSelectedYes.value,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = if (question.isSelectedYes.value) Color.Green.copy(alpha = 0.2f) else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.true_icon),
                        contentDescription = "Yes",
                        tint = Color.Green,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(Modifier.width(16.dp))
                IconButton(
                    onClick = onNoClick,
                    enabled = !question.isSelectedNo.value,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = if (question.isSelectedNo.value) Color.Red.copy(alpha = 0.2f) else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.false_icon),
                        contentDescription = "No",
                        tint = Color.Red,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}