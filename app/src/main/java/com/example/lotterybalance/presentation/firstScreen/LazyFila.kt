package com.example.lotterybalance.presentation.firstScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("SimpleDateFormat")
@Composable
fun LazyFila(
    lista: List<BoletoEntity>,
    boletoModel: BoletoViewModel
    ) {

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    // Dialogo al pulsar el icono "info"
    var showInfo by rememberSaveable { mutableStateOf(false) }
    val boleto = boletoModel.boleto

    // Lista horizontal de boletos
    LazyRow(
        modifier = Modifier.padding(1.dp, 6.dp)
    ) {
        items(lista) {boleto ->

            Card(
                modifier = Modifier
                    .size(180.dp, 280.dp)
                    .padding(4.dp),
                shape = AbsoluteRoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF413535))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                )
                {

                    // Barra superior
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 11.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = boleto.tipo,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFFF8EDD5),
                            textAlign = TextAlign.Center
                        )
                    }

                    // Multimedia
                    Column(
                        modifier = Modifier
                            .background(color = Color(0xFF665454))
                            .fillMaxWidth()
                            .size(180.dp),
                        verticalArrangement = Arrangement.Center
                    )
                    {

                        MostrarFecha(
                            texto = "Fecha:",
                            valor = formatter.format(boleto.fecha)

                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        MostrarPrecio(valor = boleto.precio)
                    }


                    // Barra inferior
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // Botones
                        IconButton(
                            onClick = {
                                showInfo = true
                                boletoModel.loadBoletoByID(boleto.numeroSerie)
                            },
                            modifier = Modifier.padding(end = 12.dp),
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color(0xFFF8EDD5))
                        ) {
                            Icon(Icons.Outlined.Info, contentDescription = "info")
                        }
                    }
                }
            }
        }
    }

    if (boleto != null){
        InfoDialog(
            show = showInfo,
            onDismiss = { showInfo = false },
            boleto = boleto,
            boletoModel = boletoModel
        )
    }
}
