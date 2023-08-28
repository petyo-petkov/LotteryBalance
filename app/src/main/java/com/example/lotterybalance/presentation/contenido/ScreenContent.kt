package com.example.lotterybalance.presentation.contenido

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.R
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoViewModel

@Composable
fun Content(boletoModel: BoletoViewModel = hiltViewModel(), ) {

    boletoModel.getBoletoEntity()

    val boletosEntity: List<BoletoEntity> by boletoModel.listaBoletosState.observeAsState(listOf())

    var precio: Double
    var gastado = 0.0
    for (boleto in boletosEntity){
        precio = boleto.precio
        gastado += precio
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .paint(
                painter = painterResource(id = R.drawable.fondo2),
                contentScale = ContentScale.FillBounds
            ),

        ) {

        Row(
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            GanadoCard(titulo = "GANADO", valor = 20.0)
            GastadoCard(titulo = "GASTADO", valor = gastado )

        }
        BalanceCard(titulo = "BALANCE", valor = 20.0 - gastado )

        LazyFila(lista = boletosEntity, boletoModel = boletoModel)


    }

}