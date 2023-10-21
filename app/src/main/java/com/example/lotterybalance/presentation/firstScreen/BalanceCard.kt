package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.text.Typography.euro

@Composable
fun BalanceCard(gastado: Double, ganado: Double) {

    val balance = ganado - gastado
    @Composable
    fun cambiarColor(valor: Double): Color {
        val colorMayor = Color(0xFF00C853)
        val colorMenor = Color(0xFFEF5350)
        val colorNeutro = MaterialTheme.colorScheme.tertiary

        val colorBalance: Color = if (valor > 0.0) {
            colorMayor
        } else if (valor == 0.0) {
            colorNeutro
        } else colorMenor

        return colorBalance

    }

    Card(
        modifier = Modifier
            .size(360.dp, 210.dp)
            .alpha(0.75F),
        shape = ShapeDefaults.Large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)

    ) {

        Fila(
            texto = "GANADO",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", ganado),
            color = MaterialTheme.colorScheme.tertiary
        )

        Fila(
            texto = "GASTADO",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", gastado),
            color = MaterialTheme.colorScheme.tertiary
        )

        Fila(
            texto = "BALANCE",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", (ganado - gastado)),
            color = cambiarColor(balance)
        )

    }


}

@Composable
fun Fila(texto: String, valor: String, color: Color) {

    Row(
        modifier = Modifier
            .size(360.dp, 68.dp)
            .fillMaxWidth(),
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
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.padding(horizontal = 70.dp))
        Text(
            text = "$valor $euro",
            modifier = Modifier
                .padding(0.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}


