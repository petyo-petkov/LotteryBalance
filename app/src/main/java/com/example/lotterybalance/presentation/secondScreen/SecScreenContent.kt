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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.presentation.firstScreen.BalanceCard
import com.example.lotterybalance.presentation.firstScreen.LazyFila
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SecScreenContent(
    startDay: Long,
    endDay: Long,
    boletoModel: BoletoViewModel = hiltViewModel()

) {
    boletoModel.sortBoletosByDate(startDay, endDay)

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    val listaBoletos = boletoModel.boletos
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
            .paint(
                painter = painterResource(id = com.example.lotterybalance.R.drawable.fondo1),
                contentScale = ContentScale.FillBounds
            )
    )
    {
        Spacer(modifier = Modifier.padding(20.dp))

        BalanceCard(gastado = gastado, ganado = ganado)

        Spacer(modifier = Modifier.padding(vertical = 24.dp))

        Text(
            color = Color(0xFFF8EDD5),
            text = "Desde:  ${formatter.format(startDay)}" +
                    "   -   " +
                    "Hasta:  ${formatter.format(endDay)}",
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        LazyFila( lista = listaBoletos )


    }
}