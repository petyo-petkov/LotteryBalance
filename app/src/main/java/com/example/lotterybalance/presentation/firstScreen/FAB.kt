package com.example.lotterybalance.presentation.firstScreen

import androidx.compose.foundation.Image
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lotterybalance.R
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
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "add",
            alignment = Alignment.Center
        )

    }
}