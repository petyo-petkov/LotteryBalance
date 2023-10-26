package com.example.lotterybalance.presentation.secondScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lotterybalance.navigation.AppScreens
import com.example.lotterybalance.viewModels.BoletoViewModel

@Composable
fun SecFAB(navController: NavController, boletoModel: BoletoViewModel = hiltViewModel()) {
    FloatingActionButton(
        onClick = {
            navController.navigate(route = AppScreens.FirstScreen.route) {
                popUpTo("first_screen") { inclusive = true }
            }

        },
        modifier = Modifier.padding(bottom = 24.dp, end = 18.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)

    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)

    }

}