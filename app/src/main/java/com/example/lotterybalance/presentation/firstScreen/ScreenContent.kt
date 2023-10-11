package com.example.lotterybalance.presentation.firstScreen

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.R
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.util.Locale

@Composable
fun Content( boletoModel: BoletoViewModel = hiltViewModel() ) {

    boletoModel.getBoletoEntity()
    boletoModel.getPremios()
    boletoModel.getAllboletosConPremios()

    val boletosPremio = boletoModel.boletosPremio
    val boletos = boletoModel.boletos
    val premios = boletoModel.premios

    var gastado = 0.0
    var ganado = 0.0

    boletos.forEach { boleto ->
        gastado += boleto.precio
    }
    premios.forEach { premio ->
        if (premio.premio != null){
            ganado += premio.premio!!
        }

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
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            GanadoCard(titulo = "GANADO",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", ganado))
            GastadoCard(titulo = "GASTADO",
                valor = String.format(locale = Locale.ENGLISH, "%.2f", gastado))

        }
        BalanceCard(
            titulo = "BALANCE",
            valor = String.format(locale = Locale.ENGLISH, "%.2f", (ganado - gastado)))

        Spacer(modifier = Modifier.padding(36.dp))
        Text(
            color = Color.White,
            text = "Últimos Boletos :",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyFila(lista = boletosPremio.takeLast(10))

    }

}