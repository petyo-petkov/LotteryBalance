package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun DialogoBorrarUno(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm()
                    onDismiss()
                },) {
                    Text(text = "BORRAR", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "CANCELAR",
                        color = Color.White)

                }
            },
            title = { Text(text = "Borrar") },
            text = { Text(
                text = "Borrar este boleto?",
                fontSize = 18.sp,
                color = Color(0xFFEF5350)) }
        )
    }
}