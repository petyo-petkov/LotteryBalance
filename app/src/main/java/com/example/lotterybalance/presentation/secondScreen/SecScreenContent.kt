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
import com.example.lotterybalance.presentation.firstScreen.LazyFila
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SecScreenContent(
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
        if (boleto.premio != null){
            ganado += boleto.premio!!
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)

    )
    {
        Spacer(modifier = Modifier.padding(12.dp))

        BalanceCard(gastado = gastado, ganado = ganado)

        Spacer(modifier = Modifier.padding(vertical = 24.dp))

        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = "Desde:  ${formatter.format(startDay)}" +
                    "   -   " +
                    "Hasta:  ${formatter.format(endDay)}",
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        LazyFila(boletoModel, lista = listaBoletos.takeLast(10).sortedBy { it.fecha } )

    }
}
