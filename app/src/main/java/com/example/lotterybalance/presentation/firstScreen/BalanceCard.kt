package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
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
fun BalanceCard(
    modifier: Modifier,
    gastado: Double,
    ganado: Double
){

    val balance = ganado - gastado
    @Composable
    fun cambiarColor(valor: Double): Color {
        val colorMayor = Color(0xFF00C853)
        val colorMenor = Color(0xFFEF5350)
        val colorNeutro = MaterialTheme.colorScheme.onPrimary

        val colorBalance: Color = if (valor > 0.0) {
            colorMayor
        } else if (valor == 0.0) {
            colorNeutro
        } else colorMenor

        return colorBalance

    }

    ElevatedCard(
        modifier = modifier
            .size(380.dp, 210.dp),
        shape = ShapeDefaults.ExtraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.elevatedCardElevation(4.dp)

    ) {

        Fila(
            modifier = modifier,
            texto = "GANADO",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", ganado),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Fila(
            modifier = modifier,
            texto = "GASTADO",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", gastado),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Fila(
            modifier = modifier,
            texto = "BALANCE",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", (ganado - gastado)),
            color = cambiarColor(balance)
        )

    }


}

@Composable
fun Fila(
    modifier: Modifier,
    texto: String,
    valor: String,
    color: Color
){

    Row(
        modifier = modifier
            .size(360.dp, 68.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = texto,
            modifier = modifier
                .padding(24.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "$valor $euro",
            modifier = modifier
                .padding(0.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}


