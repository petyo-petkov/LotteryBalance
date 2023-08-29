package com.example.lotterybalance.presentation.contenido

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.viewModels.BoletoViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val boletoModel: BoletoViewModel = hiltViewModel()
    val context = LocalContext.current
    // dialogo Borrar
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    // DatePicker
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = rememberSaveable { mutableStateOf(false) }
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }

        // DatePicker
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        snackScope.launch {
                            snackState.showSnackbar(
                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                            )
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel", color = Color.White)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }

    }

    CenterAlignedTopAppBar(
        title = { Text(text = "Loto Balance", fontWeight = FontWeight.Bold) },
        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFCCBC),
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = { isExpanded = true })
            {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
            DropMenu(
                isExpanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {

                OutlinedButton(
                    onClick = {
                        showDialog = true
                        isExpanded = false
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    border = BorderStroke(0.dp, color = Color.Transparent)

                ) {
                    Text(
                        modifier = Modifier,
                        text = "Borrar todo",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
        },
        actions = {
            IconButton(onClick = { openDialog.value = true }
            ) {

                Icon(Icons.Filled.Info, contentDescription = "Info")
            }
        }
    )

    DialogoBorrar(
        showDialog,                              // val show: Boolean
        { showDialog = false },                  // onDismiss()
        {                                  // onConfirm()
            boletoModel.deleteAll()
            Toast.makeText(context, "Se han borrado todos los boletos", Toast.LENGTH_SHORT)
                .show()
        }
    )
}


