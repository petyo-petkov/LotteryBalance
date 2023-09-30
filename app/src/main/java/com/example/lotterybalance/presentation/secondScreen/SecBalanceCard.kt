package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecBalanceCard(titulo: String, valor: String){
    val colorMayor = CardDefaults.cardColors(containerColor = Color(0xFFA5D6A7))
    val colorMenor = CardDefaults.cardColors(containerColor = Color(0xFFEF9A9A))

    val color: CardColors = if (valor.toDouble() >= 0.0){
        colorMayor
    }else colorMenor

    Card(
        modifier = Modifier
            .size(180.dp, 90.dp)
            .padding(8.dp),
        colors = color

    ) {
        Text(
            text = titulo,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )

        Text(
            text = "$valor ${Typography.euro}",
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

}