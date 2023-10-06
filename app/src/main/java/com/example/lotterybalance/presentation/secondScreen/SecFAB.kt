package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lotterybalance.navigation.AppScreens

@Composable
fun SecFAB(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(route = AppScreens.FirstScreen.route) {
                popUpTo("first_screen") { inclusive = true }
            }
        },
        modifier = Modifier.padding(bottom = 24.dp, end = 18.dp),
        containerColor = Color(0xFFFFAB91),
        contentColor = Color.Black,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)

    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)

    }

}