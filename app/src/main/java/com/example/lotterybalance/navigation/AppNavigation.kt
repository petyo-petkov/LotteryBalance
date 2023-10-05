package com.example.lotterybalance.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lotterybalance.presentation.FirstScreen
import com.example.lotterybalance.presentation.SecondScreen
import com.example.lotterybalance.viewModels.BoletoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val boletoModel: BoletoViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {

        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)

        }
        composable(
            route = AppScreens.SecondScreen.route ) {
            SecondScreen(navController, boletoModel)

        }
    }

}
