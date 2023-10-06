package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.viewModels.MainViewModel

@Composable
fun FAB(viewModel: MainViewModel = hiltViewModel()) {

    FloatingActionButton(
        onClick = { viewModel.startScanning() },
        modifier = Modifier,
        containerColor = Color(0xFFFFAB91),
        contentColor = Color.Black,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        Icon(Icons.Filled.Add, null)

    }
}
