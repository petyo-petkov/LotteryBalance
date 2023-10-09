package com.example.lotterybalance.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.lotterybalance.presentation.secondScreen.SecFAB
import com.example.lotterybalance.presentation.secondScreen.SecScreenContent
import com.example.lotterybalance.viewModels.BoletoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(
    navController: NavController,
    boletoModel: BoletoViewModel,
    startDay: Long,
    endDay: Long
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF4D4646),
        content = { SecScreenContent(boletoModel, startDay, endDay) },
        floatingActionButton = {  SecFAB(navController) }

        )
}