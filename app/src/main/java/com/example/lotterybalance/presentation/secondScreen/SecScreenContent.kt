package com.example.lotterybalance.presentation.secondScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import com.example.lotterybalance.R
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

    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val listaSortidoBoletos = boletoModel.sortidoBoletos
    var gastado = 0.0
    var ganado = 0.0

    listaSortidoBoletos.forEach {boleto ->
        gastado += boleto.boleto.precio
        ganado += boleto.premio.premio!!
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .paint(
                painter = painterResource(id = R.drawable.fondo1),
                contentScale = ContentScale.FillBounds
            )
    )
    {
        Spacer(modifier = Modifier.padding(10.dp))

        InfoBalanceCard(gastado = gastado, ganado = ganado)

        Spacer(modifier = Modifier.padding(6.dp))

        Column(modifier = Modifier.padding(vertical = 26.dp)) {
            Text(
                color = Color.White,
                text = "Desde: ${formatter.format(startDay)} ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                color = Color.White,
                text = "Hasta: ${formatter.format(endDay)}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyFila(lista = listaSortidoBoletos)

    }
}