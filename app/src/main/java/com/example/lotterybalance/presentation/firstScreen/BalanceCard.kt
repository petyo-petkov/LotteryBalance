package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
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

@Composable
fun BalanceCard(gastado: Double, ganado: Double) {

    val balance = ganado - gastado
    fun cambiarColor(valor: Double): Color {
        val colorMayor = Color(0xFF00C853)
        val colorMenor = Color(0xFFEF5350)
        val colorNeutro = Color(0xFFF8EDD5)

        val colorBalance: Color = if (valor > 0.0) {
            colorMayor
        }else if (valor == 0.0){
            colorNeutro
        }
        else colorMenor

        return colorBalance

    }

    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Fila(
            texto = "Ganado",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", ganado),
            color = Color(0xFFF8EDD5)
        )

        Fila(
            texto = "Gastado",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", gastado),
            color = Color(0xFFF8EDD5)
        )

        Fila(
            texto = "Balance",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", (ganado - gastado)),
            color = cambiarColor(balance)
        )

    }


}

@Composable
fun Fila(texto: String, valor: String, color: Color) {

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .size(360.dp, 60.dp),
        shape = ShapeDefaults.ExtraLarge
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF413535)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = texto,
                modifier = Modifier
                    .padding(0.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8EDD5)
            )
            Spacer(modifier = Modifier.padding(horizontal = 80.dp))
            Text(
                text = "$valor ${Typography.euro}",
                modifier = Modifier
                    .padding(0.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }


}