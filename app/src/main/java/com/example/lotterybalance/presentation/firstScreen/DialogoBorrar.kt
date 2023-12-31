package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogoBorrar(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AnimatedVisibility (
        visible = show,
        enter = slideInVertically(),
        exit = slideOutVertically()
        ) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm()
                    onDismiss()
                                     },) {
                    Text(text = "BORRAR", color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "CANCELAR",
                        color = MaterialTheme.colorScheme.onPrimary)

                }
            },
            title = { Text(text = "Borrar") },
            text = { Text(text = "Borrar todos los boletos?") }
        )
    }
}