package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun Content( boletoModel: BoletoViewModel = hiltViewModel() ) {

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
            ganado += premio.premio
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.fondo1),
                contentScale = ContentScale.FillBounds
            )
    )
    {

        Spacer(modifier = Modifier.padding(10.dp))

        BalanceCard(gastado = gastado, ganado = ganado)

        Spacer(modifier = Modifier.padding(18.dp))

        Text(
            color = Color(0xFFF8EDD5),
            text = "Últimos Boletos :",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))

        LazyFila(lista = boletosPremio.takeLast(10))

    }

}