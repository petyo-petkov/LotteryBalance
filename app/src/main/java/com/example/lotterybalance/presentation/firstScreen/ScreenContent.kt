package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.viewModels.BoletoViewModel

@Composable
fun ScreenContent(
    modifier: Modifier,
    boletoModel: BoletoViewModel
) {

    val listaBoletos = boletoModel.boletosListState.value.estadoBoletos


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
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)

    )
    {
        Spacer(modifier = modifier.padding(12.dp))

        BalanceCard(modifier = modifier, gastado = gastado, ganado = ganado)

        Spacer(modifier = modifier.padding(24.dp))

        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = "Últimos Boletos :",
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = modifier.padding(6.dp))

        if (listaBoletos.isEmpty()){
            Text(
                text = "Usa '+', para escanear tus boletos",
                modifier = modifier.padding(top = 120.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }
            Pager(boletoModel = boletoModel, lista = listaBoletos.takeLast(10))

    }
}
