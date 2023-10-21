package com.example.lotterybalance.presentation.firstScreen

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
    boletoModel: BoletoViewModel,
    show: Boolean,
    onDismiss: () -> Unit

) {
    val boleto = boletoModel.boletoState.value.boletoState
    val context = LocalContext.current
    var showBorrar by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    if (show && boleto != null) {

        var valor by rememberSaveable { mutableStateOf("") }
        val ganado = valor.toDoubleOrNull()


        AlertDialog(
            modifier = Modifier,
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Surface(
                modifier = Modifier,
                shape = ShapeDefaults.ExtraLarge,
                color = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.tertiary,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                //border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.secondary)
            ) {
                Column(
                    modifier = Modifier
                        .size(320.dp, 460.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        // Tipo de Boleto
                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(12.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        item {
                            Text(
                                text = "Boleto:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            Text(text = boleto.tipo, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                        }
                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(12.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Numero de Serie
                        item {
                            Text(
                                text = "Numero de serie:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            Text(
                                text = boleto.numeroSerie.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(12.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Fecha
                        item {
                            Text(
                                text = "Fecha:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            Text(
                                text = formatter.format(boleto.fecha),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(12.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Precio
                        item {
                            Text(
                                text = "Precio:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            Text(
                                text = "${boleto.precio} $euro ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(12.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Combinaciones
                        item {
                            Text(
                                text = "Numeros:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            boleto.combinaciones.forEach { item ->
                                Text(
                                    text = item,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(12.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Reintegro
                        if (boleto.reintegro != null) {
                            item {
                                Text(
                                    text = "Reintegro:",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                            item {
                                Text(
                                    text = boleto.reintegro.toString(),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            item {
                                HorizontalDivider(
                                    modifier = Modifier.padding(12.dp),
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }


                        // Premio Ganado
                        item {
                            Text(
                                text = "Premio:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            var texto = "0.0"
                            if (boleto.premio != null) {
                                texto = boleto.premio.toString()
                            }

                            Text(
                                text = "$texto $euro",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            OutlinedTextField(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Create,
                                        contentDescription = null
                                    )
                                },
                                value = valor,
                                onValueChange = {
                                    if (it.isEmpty() || it.toDoubleOrNull() != null) {
                                        valor = it
                                    }
                                },
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = { Text(text = "Ingresar o corregir premio:") },
                                placeholder = { Text(text = "0.00 $euro") },
                                shape = ShapeDefaults.Large,
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                colors = OutlinedTextFieldDefaults
                                    .colors(
                                        focusedBorderColor = Color(0xFFFFCCBC),
                                        focusedLabelColor = Color(0xFFFFCCBC),
                                        focusedLeadingIconColor = Color(0xFFFFCCBC)
                                    )
                            )
                        }
                    }
                }
                // Botones
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 460.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom

                ) {
                    IconButton(
                        onClick = {
                            boleto.premio = ganado
                            coroutineScope.launch {
                                if (ganado != null) {
                                    boletoModel.updatePremio(boleto)
                                }
                            }
                            onDismiss()
                        },
                        modifier = Modifier,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color(0xFF00C853)
                        )
                    ) {
                        Icon(Icons.Default.Done, contentDescription = "done")

                    }

                    Spacer(modifier = Modifier.width(160.dp))

                    IconButton(
                        onClick = {
                            showBorrar = true
                        },
                        modifier = Modifier,
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
            boletoModel.deleteOneBoleto(boleto)
            showBorrar = false
            onDismiss()
            Toast.makeText(context, "Se ha borrado el boleto", Toast.LENGTH_SHORT).show()
        }
    )

}

