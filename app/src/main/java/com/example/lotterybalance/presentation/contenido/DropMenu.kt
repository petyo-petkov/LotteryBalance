package com.example.lotterybalance.presentation.contenido

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DropMenu(
    isExpanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onDismissRequest() },
        modifier = Modifier
            .background(color = Color(0xFFFFAB91))
    )
    {
        content()
    }
}