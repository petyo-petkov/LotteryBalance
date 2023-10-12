package com.example.lotterybalance.presentation.secondScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.presentation.firstScreen.LazyFila
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SecScreenContent(
    boletoModel: BoletoViewModel,
    startDay: Long,
    endDay: Long
) {
    boletoModel.sortBoletosByDate(startDay, endDay)
    boletoModel.getAllboletosConPremios()

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    val listaSortidoBoletos = boletoModel.sortidoBoletos
    var gastado = 0.0
    var ganado = 0.0

    listaSortidoBoletos.forEach { boleto ->
        gastado += boleto.boleto.precio
        if (boleto.premioEntity != null) {
            ganado += boleto.premioEntity.premio!!
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = com.example.lotterybalance.R.drawable.fondo1),
                contentScale = ContentScale.FillBounds
            )
        //.background(color = Color(0xFF110F0F))
    )
    {
        Spacer(modifier = Modifier.padding(10.dp))

        InfoBalanceCard(gastado = gastado, ganado = ganado)

        Spacer(modifier = Modifier.padding(vertical = 24.dp))

        Text(
            color = Color(0xFFF8EDD5),
            text = "Desde:  ${formatter.format(startDay)}" +
                    "   -   " +
                    "Hasta:  ${formatter.format(endDay)}",
            modifier = Modifier

                .padding(8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        LazyFila(lista = listaSortidoBoletos)

    }
}