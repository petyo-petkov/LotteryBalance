package com.example.lotterybalance.presentation.secondScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.presentation.firstScreen.BalanceCard
import com.example.lotterybalance.presentation.firstScreen.Pager
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SecScreenContent(
    modifier: Modifier,
    boletoModel: BoletoViewModel,
    startDay: Long,
    endDay: Long,
) {
    boletoModel.getBoletosByDates(startDay, endDay)

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    val listaBoletos = boletoModel.sortidoState.value.sortidoState

    var gastado = 0.0
    var ganado = 0.0

    listaBoletos.forEach { boleto ->
        gastado += boleto.precio
        if (boleto.premio != null) {
            ganado += boleto.premio!!
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        Spacer(modifier = modifier.padding(12.dp))

        BalanceCard(modifier = modifier, gastado = gastado, ganado = ganado)

        Spacer(modifier = modifier.padding(vertical = 24.dp))

        Text(
            modifier = modifier.padding(0.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            text = "Desde:  ${formatter.format(startDay)}" +
                    "   -   " +
                    "Hasta:  ${formatter.format(endDay)}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = modifier.padding(vertical = 6.dp))

        if (listaBoletos.isEmpty()) {
            Text(
                text = " No hay boletos en este rango de fechas",
                modifier = modifier.padding(top = 120.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }
            Pager(boletoModel = boletoModel, lista = listaBoletos)
    }
}
