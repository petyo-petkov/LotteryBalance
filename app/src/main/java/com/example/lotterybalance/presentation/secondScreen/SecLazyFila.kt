package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.R
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.presentation.firstScreen.InfoDialog
import com.example.lotterybalance.presentation.firstScreen.MostrarFecha
import com.example.lotterybalance.presentation.firstScreen.MostrarPrecio
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch

@Composable
fun SecLazyFila(
    lista: List<BoletoEntity>,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Dialogo al pulsar el icono "info"
    var showInfo by rememberSaveable { mutableStateOf(false) }
   // val _boleto = boletoModel.boleto
    //val _premio = boletoModel.premio


    // Lista horizontal de boletos
    LazyRow(
        modifier = Modifier.padding(1.dp, 6.dp)
    ) {
        items(lista) { boleto ->

            Card(
                modifier = Modifier
                    .alpha(0.9f)
                    .size(220.dp, 320.dp)
                    .padding(4.dp),
                shape = AbsoluteRoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            1.dp,
                            Color(0xFF000000),
                            shape = AbsoluteRoundedCornerShape(20.dp)
                        )
                )
                {
                    // Barra superior
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Miniatura
                        Box(
                            modifier = Modifier
                                .background(color = Color.Black, shape = CircleShape)
                                .size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        // Encabezado
                        Box(
                            modifier = Modifier.size(130.dp, 41.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = boleto.tipo,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp
                            )
                        }

                    }

                    // Multimedia
                    Column(
                        modifier = Modifier
                            .border(1.dp, Color(0xFF000000))
                            .background(color = Color(0xFFF3E5F5))
                            .fillMaxWidth()
                            .size(200.dp),
                        verticalArrangement = Arrangement.Center
                    )
                    {

                        MostrarFecha(
                            texto = "Fecha:",
                            valor = boleto.fecha.toString()

                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        MostrarPrecio(valor = boleto.precio)
                    }

                    Spacer(modifier = Modifier.height(0.dp))

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
                                /*
                                coroutineScope.launch {
                                    boletoModel.loadBoletoByID(boleto.numeroSerie)
                                    boletoModel.loadPremioById(boleto.numeroSerie)
                                }
                                 */
                            },
                            modifier = Modifier.padding(end = 12.dp),
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black)
                        ) {
                            Icon(Icons.Default.Info, contentDescription = "info")
                        }

                    }

                }
            }
        }
    }

}
