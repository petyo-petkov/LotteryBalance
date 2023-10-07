package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Boletos seleccionados",
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )
        },

        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFCCBC),
            actionIconContentColor = Color.Black,
            titleContentColor = Color.Black
        )

    )

}