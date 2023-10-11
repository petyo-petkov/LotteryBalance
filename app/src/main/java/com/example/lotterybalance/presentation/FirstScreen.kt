package com.example.lotterybalance.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lotterybalance.presentation.firstScreen.BottomBar
import com.example.lotterybalance.presentation.firstScreen.Content
import com.example.lotterybalance.viewModels.BoletoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController,  boletoModel: BoletoViewModel = hiltViewModel())
{
    boletoModel.getBoletoEntity()
    boletoModel.getAllboletosConPremios()
    boletoModel.getPremios()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF4D4646),
        content = { Content() },
        bottomBar = { BottomBar(navController) },

    )
}