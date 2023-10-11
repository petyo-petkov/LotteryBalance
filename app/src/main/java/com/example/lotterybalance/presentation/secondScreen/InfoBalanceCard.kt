package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.text.Typography.euro

@Composable
fun InfoBalanceCard(gastado: Double, ganado: Double) {

    val balance = ganado - gastado
    fun cambiarColor(valor: Double): Color {
        val colorMayor = Color(0xFFA5D6A7)
        val colorMenor = Color(0xFFEF9A9A)

        val colorBalance: Color = if (valor >= 0.0) {
            colorMayor
        } else colorMenor

        return colorBalance

    }

    OutlinedCard(
        modifier = Modifier
            .size(360.dp, 200.dp),
        shape = ShapeDefaults.ExtraLarge,
        elevation = CardDefaults.cardElevation(20.dp),
        border = BorderStroke(1.dp, color = Color.DarkGray)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Fila(
                texto = "GANADO",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", ganado),
                color = Color(0xFFC8E6C9)
            )

            Fila(
                texto = "GASTADO",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", gastado),
                color = Color(0xFFFFCDD2)
            )

            Fila(
                texto = "BALANCE",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", (ganado - gastado)),
                color = cambiarColor(balance)
            )

        }
    }

}

@Composable
fun Fila(texto: String, valor: String, color: Color) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
        , horizontalArrangement = Arrangement.Center
        ,verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = texto,
            modifier = Modifier
                .padding(21.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(horizontal = 50.dp))
        Text(
            text = "$valor $euro",
            modifier = Modifier
                .padding(21.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

