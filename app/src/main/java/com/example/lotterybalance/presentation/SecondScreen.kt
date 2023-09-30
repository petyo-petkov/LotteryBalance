package com.example.lotterybalance.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.lotterybalance.presentation.secondScreen.SecBottomBar
import com.example.lotterybalance.presentation.secondScreen.SecScreenContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF4D4646),
        content = { SecScreenContent(navController) },
        bottomBar = { SecBottomBar(navController) },

        )
}