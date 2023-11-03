package com.example.lotterybalance.presentation.firstScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lotterybalance.navigation.AppScreens
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("UnrememberedMutableState", "SimpleDateFormat", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    modifier: Modifier,
    boletoModel: BoletoViewModel,
    navController: NavController
){

    val context = LocalContext.current
    val boletos = boletoModel.boletosListState.value.estadoBoletos

    // dialogo Borrar
    var showDialog by rememberSaveable { mutableStateOf(false) }

    // DatePicker
    var openDialog by rememberSaveable { mutableStateOf(false) }
    val formatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH) }
    val state = rememberDateRangePickerState()


    AnimatedVisibility (
        visible = openDialog,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        val startDay by remember(openDialog) { derivedStateOf{ state.selectedStartDateMillis }}
        val endDay by remember(openDialog) { derivedStateOf{ state.selectedEndDateMillis }}

        DatePickerDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        navController.navigate(route = "${AppScreens.SecondScreen.route}/${startDay}/${endDay}")
                    },
                    enabled = state.selectedEndDateMillis != null
                ) {
                    Text(text = "Ok", color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            modifier = modifier,
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        state.setSelection(startDateMillis = null, endDateMillis = null)
                    }
                ) {
                    Text("Cancel", color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            shape = ShapeDefaults.Medium,
        ) {
            DateRangePicker(
                state = state,
                modifier = modifier.weight(1f),
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    todayContentColor = MaterialTheme.colorScheme.onPrimary,
                    todayDateBorderColor = MaterialTheme.colorScheme.onPrimary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.secondary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.tertiary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        text = "Selecionar el rango de fechas",
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                headline = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Box(modifier.padding(horizontal = 12.dp)) {
                            (if (state.selectedStartDateMillis != null)
                                state.selectedStartDateMillis?.let { formatter.format(it) }
                            else "Fecha inicial")?.let { Text(text = it, fontSize = 16.sp) }
                        }
                        Box(modifier.padding(horizontal = 12.dp)) {
                            (if (state.selectedEndDateMillis != null)
                                state.selectedEndDateMillis?.let { formatter.format(it) }
                            else "Fecha final")?.let { Text(text = it, fontSize = 16.sp) }
                        }

                    }
                }

            )
        }


    }

    BottomAppBar(
        actions = {
            // info
            IconButton(onClick = {
                Toast.makeText(context, "Gastos Loteria v1.0", Toast.LENGTH_LONG).show()
            }
            ) {
                Icon(Icons.Outlined.Info, contentDescription = null)
            }

            // sel. fechas
            IconButton(onClick = { openDialog = true }
            )
            {
                Icon(Icons.Outlined.DateRange, contentDescription = null)
            }

            // delete
            IconButton(
                onClick = { showDialog = true },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color(0xFFF44336)
                )
            ) {
                Icon(Icons.Filled.Delete, contentDescription = null)
            }

        },
        modifier = modifier,
        floatingActionButton = { FAB(modifier) },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        contentPadding = PaddingValues(horizontal = 12.dp)
    )


    DialogoBorrar(
       show =  showDialog,
       onDismiss =  { showDialog = false },
       onConfirm =  {
            boletoModel.deleteAllBoletos(boletos)
            Toast.makeText(context, "Se han borrado todos los boletos", Toast.LENGTH_SHORT)
                .show()
        }
    )

}

