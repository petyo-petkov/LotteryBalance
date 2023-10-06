package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SecScreenContent(
    boletoModel: BoletoViewModel,
    startDay: Long,
    endDay: Long

) {

    boletoModel.sortBoletosByDate(startDay, endDay )
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    val listaSortidoBoletos = boletoModel.sortidoBoletos
    var gastado = 0.0
    val ganado = 0.0

    listaSortidoBoletos.forEach {
        gastado += it.precio
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
        Row(
            modifier = Modifier
                .padding(top = 90.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SecGanadoCard(
                titulo = "GANADO",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", ganado)
            )
            SecGastadoCard(
                titulo = "GASTADO",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", gastado)
            )

        }
        SecBalanceCard(
            titulo = "BALANCE",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", (ganado - gastado))
        )

        Spacer(modifier = Modifier.padding(18.dp))

        Column{
            Text(
                color = Color.White,
                text = "Desde: ${formatter.format(startDay)} ",
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                color = Color.White,
                text = "Hasta: ${formatter.format(endDay)}",
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SecLazyFila(lista = listaSortidoBoletos)

    }
}