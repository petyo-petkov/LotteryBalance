package com.example.lotterybalance.presentation.firstScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lotterybalance.navigation.AppScreens
import com.example.lotterybalance.viewModels.BoletoViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("UnrememberedMutableState", "SimpleDateFormat", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavController) {
    val boletoModel: BoletoViewModel = hiltViewModel()
    val context = LocalContext.current

    // dialogo Borrar
    var showDialog by rememberSaveable { mutableStateOf(false) }

    // DatePicker
    var openDialog by rememberSaveable { mutableStateOf(false) }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val state = rememberDateRangePickerState()
    val startDay = state.selectedStartDateMillis
    val endDay = state.selectedEndDateMillis

    BottomAppBar(
        actions = {
            // delete
            IconButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Filled.Delete, contentDescription = null)
            }
            // sel. fechas
            IconButton(onClick = { openDialog = true }) {
                Icon(Icons.Filled.DateRange, contentDescription = null)
            }
            // info
            IconButton(onClick = {
                Toast.makeText(context, "Lottery Balance App v.1.0", Toast.LENGTH_LONG).show()
            }
            ) {
                Icon(Icons.Filled.Info, contentDescription = null)
            }
        },
        modifier = Modifier,
        floatingActionButton = { FAB() },
        containerColor = Color(0xFF413535),
        contentColor = Color(0xFFF8EDD5),
        contentPadding = PaddingValues(horizontal = 6.dp)
    )

    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        navController.navigate(route = "${AppScreens.SecondScreen.route}/${startDay}/${endDay}" )
                    },
                    enabled = state.selectedEndDateMillis != null
                ) {
                    Text(text = "Ok", color = Color.White)
                }
            },
            modifier = Modifier,
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text("Cancel", color = Color.White)
                }
            }
        ) {
            DateRangePicker(
                state = state,
                modifier = Modifier.weight(1f),
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.White,
                    todayDateBorderColor = Color(0xFFFFAB91),
                    selectedDayContainerColor = Color(0xFFFFAB91),
                    dayInSelectionRangeContainerColor = Color(0xFFFFCCBC),
                    dayInSelectionRangeContentColor = Color(0xFF5F5D5D),

                    ),
                title = {
                    Text(
                        text = "Selecionar el rango de fechas", modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                headline = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Box(Modifier.weight(1f)) {
                            (if (state.selectedStartDateMillis != null)
                                state.selectedStartDateMillis?.let { formatter.format(it) }
                            else "Fecha inicial")?.let { Text(text = it, fontSize = 16.sp) }
                        }
                        Box(Modifier.weight(1f)) {
                            (if (state.selectedEndDateMillis != null)
                                state.selectedEndDateMillis?.let { formatter.format(it) }
                            else "Fecha final")?.let { Text(text = it, fontSize = 16.sp) }
                        }
                        Box(Modifier.weight(0.2f)) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Okk")
                        }
                    }
                },

            )
        }
    }


    DialogoBorrar(
        showDialog,
        { showDialog = false },
        {
            boletoModel.deleteAllBoletos()
            boletoModel.deletePremios()
            Toast.makeText(context, "Se han borrado todos los boletos", Toast.LENGTH_SHORT)
                .show()
        }
    )

}