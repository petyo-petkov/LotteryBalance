package com.example.lotterybalance.presentation.firstScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("SimpleDateFormat", "RememberReturnType")
@Composable
fun LazyFila(
    boletoModel: BoletoViewModel,
    lista: List<BoletoEntity>

) {
    val formatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH) }
    val coroutine = rememberCoroutineScope()

    // Dialogo al pulsar el icono "info"
    var showInfo by rememberSaveable { mutableStateOf(false) }

    // Lista horizontal de boletos
    LazyRow(
        modifier = Modifier.padding(2.dp, 6.dp)
    ) {
        items(lista, key = {it.numeroSerie}) { boleto ->

           BoletoCard(
               tipo = boleto.tipo,
               fecha = formatter.format(boleto.fecha),
               precio = boleto.precio,
               onConfirm = {
                   showInfo = true
                   coroutine.launch {
                       boletoModel.loadBoletoByID(boleto.numeroSerie)
                   }
               }
           )

        }
    }
    InfoDialog(
        boletoModel,
        show = showInfo,
        onDismiss = { showInfo = false }
    )

}