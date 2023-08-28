package com.example.lotterybalance.presentation.contenido

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val boletoModel: BoletoViewModel = hiltViewModel()
    val context = LocalContext.current
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }


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
            IconButton(onClick = { }
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
            Toast.makeText(context, "Se han borrado todos los boletos", Toast.LENGTH_SHORT).show()
        })

}
