package com.example.lotterybalance.presentation.firstScreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lotterybalance.database.entities.BoletoEntity
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("SimpleDateFormat", "RememberReturnType")
@Composable
fun LazyFila(
    boletoModel: BoletoViewModel,
    lista: List<BoletoEntity>

) {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    // Dialogo al pulsar el icono "info"
    var showInfo by rememberSaveable { mutableStateOf(false) }


    // Lista horizontal de boletos
    LazyRow(
        modifier = Modifier.padding(2.dp, 6.dp)
    ) {
        items(lista) { boleto ->

            ElevatedCard(
                Modifier
                    .clickable {
                        showInfo = true
                        boletoModel.loadBoletoByID(boleto.numeroSerie)
                    }
                    .size(160.dp, 240.dp)
                    .padding(4.dp),
                shape = AbsoluteRoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = boleto.tipo,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )

                }
                // Multimedia
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.secondary)
                        .fillMaxSize(),
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

            }

        }
    }
    InfoDialog(
        boletoModel,
        show = showInfo,
        onDismiss = { showInfo = false }
    )

}
