package com.example.lotterybalance.presentation.contenido

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Date
import kotlin.text.Typography.euro


@Composable
fun MostrarFecha(texto: String, valor: String){
    Text(
        text = texto,
        fontSize = 22.sp,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        fontFamily = FontFamily.Default
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = valor.toString(),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Black,

    )
}

@Composable
fun MostrarPrecio(valor: Double){
    Text(
        text = "Precio:",
        fontSize = 22.sp,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        fontFamily = FontFamily.Default
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = "$valor $euro",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Black,

        )
}