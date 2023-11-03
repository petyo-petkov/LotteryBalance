package com.example.lotterybalance.presentation.firstScreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.text.Typography.euro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoDialog(
    modifier: Modifier,
    boletoModel: BoletoViewModel,
    show: Boolean,
    onDismiss: () -> Unit

) {
    val boleto = boletoModel.boletoState.value.estadoBoleto
    val context = LocalContext.current
    var showBorrar by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val formatter = rememberSaveable { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH) }
    val listState = rememberLazyListState()

    AnimatedVisibility(
        visible = show,
        enter = slideInVertically(),
        exit = slideOutVertically()

    ) {
        var valor by rememberSaveable { mutableStateOf("") }
        //val ganado = valor.toDoubleOrNull()
        val ganado by remember { derivedStateOf { valor.toDoubleOrNull() } }

        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismiss() }
        ) {
            Surface(
                modifier = modifier,
                shape = ShapeDefaults.Large,
                color = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(
                    modifier = modifier
                        .size(320.dp, 460.dp)
                ) {
                    LazyColumn(
                        modifier = modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        state = listState,
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        // Tipo de Boleto
                        item {
                            ItemContent(
                                nombre = "Boleto: ",
                                valor = boleto.tipo,
                                lista = null
                            )
                        }

                        // Numero de Serie
                        item {
                            ItemContent(
                                nombre = "Número de serie: ",
                                valor = "${boleto.numeroSerie}",
                                lista = null
                            )
                        }

                        // Fecha
                        item {
                            ItemContent(
                                nombre = "Fecha: ",
                                valor = formatter.format(boleto.fecha),
                                lista = null
                            )
                        }

                        // Precio
                        item {
                            ItemContent(
                                nombre = "Precio: ",
                                valor = "${boleto.precio} $euro ",
                                lista = null
                            )
                        }

                        // Combinaciones
                        item {
                            ItemContent(
                                nombre = "Combinacíones: ",
                                valor = null,
                                lista = boleto.combinaciones
                            )
                        }

                        // Reintegro
                        boleto.reintegro?.let {
                            item {
                                ItemContent(
                                    nombre = "Reintegro: ",
                                    valor = "${boleto.reintegro}",
                                    lista = null
                                )
                            }
                        }

                        // Premio Ganado
                        item {
                            val texto = boleto.premio?.toString() ?: "0.0"
                            ItemContent(
                                nombre = "Premio: ",
                                valor = "$texto $euro",
                                lista = null
                            )
                            OutlinedTextField(
                                value = valor,
                                onValueChange = {
                                    if (it.isEmpty() || it.toDoubleOrNull() != null) {
                                        valor = it
                                    }
                                },
                                modifier = modifier.padding(bottom = 8.dp),
                                label = { Text(text = "Ingresar o corregir premio:") },
                                placeholder = { Text(text = "0.00 $euro") },
                                shape = ShapeDefaults.Large,
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                colors = OutlinedTextFieldDefaults
                                    .colors(
                                        focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                        focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                                    )
                            )
                        }
                    }
                }
                // Botones
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 460.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom

                ) {
                    IconButton(
                        onClick = {
                            boleto.premio = ganado
                            coroutineScope.launch {
                                ganado?.let { boletoModel.insertOneBoleto(boleto) }
                            }
                            onDismiss()
                        },
                        modifier = modifier,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color(0xFF00C853)
                        )
                    ) {
                        Icon(Icons.Default.Done, contentDescription = "done")

                    }

                    Spacer(modifier = modifier.width(160.dp))

                    IconButton(
                        onClick = {
                            showBorrar = true
                        },
                        modifier = modifier,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color(
                                0xFFF44336
                            )
                        )
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "delete")

                    }

                }

            }

        }

    }

    DialogoBorrarUno(
        show = showBorrar,
        onDismiss = { showBorrar = false },
        onConfirm = {
            showBorrar = false
            boletoModel.deleteOneBoleto(boleto)
            onDismiss()
            Toast.makeText(context, "Se ha borrado el boleto", Toast.LENGTH_SHORT).show()
        }
    )

}

@Composable
fun ItemContent(
    modifier: Modifier = Modifier,
    nombre: String,
    valor: String?,
    lista: List<String>?
) {

    HorizontalDivider(
        modifier = modifier.padding(12.dp),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.onPrimary
    )
    Text(
        text = nombre,
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold
    )
    Spacer(modifier = modifier.height(10.dp))

    if (lista != null) {
        lista.forEach {
            Text(
                text = it,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

    } else {
        if (valor != null) {
            Text(
                text = valor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

