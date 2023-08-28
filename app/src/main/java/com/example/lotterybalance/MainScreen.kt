package com.example.lotterybalance

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lotterybalance.presentation.contenido.Content
import com.example.lotterybalance.presentation.contenido.FAB
import com.example.lotterybalance.presentation.contenido.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen()
{
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF4D4646),
        topBar = { TopBar() },
        content = { Content() },
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End

    )
}